package com.recipes.api.service.implementations;

import com.recipes.api.dtos.*;
import com.recipes.api.entity.UserIngredientEntity;
import com.recipes.api.entity.UserRecommendationEntity;
import com.recipes.api.entity.enums.CookingLevel;
import com.recipes.api.entity.enums.UnitOfMeasure;
import com.recipes.api.repository.*;
import com.recipes.api.utility.IngredientKey;

import com.recipes.api.exceptions.NotFoundException;
import com.recipes.api.service.interfaces.UserRecommendationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static com.recipes.api.common.Constants.*;

@Service
@RequiredArgsConstructor
@Log
public class UserRecommendationServiceImpl implements UserRecommendationService {
    private final UserRecommendationsRepository userRecommendationsRepository;
    private final UserRepository userRepository;
    private final UserIngredientRepository userIngredientRepository;
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    @Override
    public UserRecommendationDto getUserRecommendationsByDate(Integer id, LocalDateTime dateTime) {
        return userRecommendationsRepository.findByDateTimeIsAndUserId(dateTime, id)
                .map(UserRecommendationDto::fromEntity)
                .orElseThrow(() -> new NotFoundException(String.format(USER_RECOMMENDATION_NOT_FOUND)));
    }

    @Override
    public void getUserRecommendationsByInterval(Integer id, LocalDateTime startDate, LocalDateTime endDate) {
        UserDto userDto = userRepository
                .findById(id)
                .map(UserDto::fromEntity)
                .orElseThrow(() -> new NotFoundException(String.format(USER_NOT_FOUND, id)));

        List<RecipeDto> recipeDtos = recipeRepository.findAll().stream().map(RecipeDto::fromEntity).toList();

        // Pasul 1 - generam recomandarile
        Map<String, List<RecipeDto>> userRecommendationDtos = getRecommendations(userDto, recipeDtos, startDate, endDate, 3);

        // Pasul 2 - adaugam in BD recomandarile
        saveUserRecommendations(userRecommendationDtos, id);

        // Pasul 3 - vom crea lista de cumparaturi
        calculateUserCart(userRecommendationDtos, userDto);
    }

    private Integer getRecipeId(List<RecipeDto> recipes, int index) {
        return (index >= 0 && index < recipes.size()) ? recipes.get(index).getId() : 0;
    }

    public void calculateUserCart(Map<String, List<RecipeDto>> userRecommendationDtos, UserDto userDto) {
        // Creează o structură de date pentru lista de cumpărături
        Map<IngredientKey, Integer> shoppingList = new HashMap<>();
        List<UserIngredientDto> fridgeUserIngredients = userDto.getUserIngredients()
                .stream()
                .filter(ingredient -> !ingredient.getIsCartIngredient())
                .toList();

        // Iterăm prin fiecare zi și rețetă pentru a aduna ingredientele
        for (Map.Entry<String, List<RecipeDto>> entry : userRecommendationDtos.entrySet()) {
            List<RecipeDto> recipeDtosList = entry.getValue();

            for (RecipeDto recipeDto : recipeDtosList) {

                // Iterăm prin fiecare ingredient din rețetă
                for (RecipeIngredientDto ingredientDto : recipeDto.getIngredientDtoList()) {
                    IngredientDto ingredient = ingredientDto.getIngredient();
                    double ingredientQuantity = ingredientDto.getQuantity();
                    UnitOfMeasure unitOfMeasure = ingredientDto.getUnitOfMeasure();

                    // Creăm cheia pentru ingredient
                    IngredientKey ingredientKey = new IngredientKey(ingredient.getId(), unitOfMeasure);

                    // Verificăm dacă ingredientul există deja în lista de cumpărături
                    if (shoppingList.containsKey(ingredientKey)) {
                        // Dacă există, adăugăm cantitatea la cantitatea existentă
                        int currentQuantity = shoppingList.get(ingredientKey);
                        shoppingList.put(ingredientKey, (int) (currentQuantity + ingredientQuantity));
                    } else {
                        // Dacă nu există, adăugăm ingredientul în listă
                        shoppingList.put(ingredientKey, (int) ingredientQuantity);
                    }
                }
            }
        }

        // TO DO: Iteram prin lista de cumparaturi si verificam ce ingrediente sunt deja in frigider si in ce cantitate
        // daca este si este in cantitate suficienta, nu il adaugam in lista de cumparaturi
        // daca este si nu este in cantitate suficienta, adaugam in lista de cumparaturi doar diferenta

        for (Map.Entry<IngredientKey, Integer> entry : shoppingList.entrySet()) {
            IngredientKey ingredientKey = entry.getKey();
            int requiredQuantity = entry.getValue();

            // Căutăm ingredientul în frigider
            UserIngredientDto fridgeIngredient = findIngredientInFridge(fridgeUserIngredients, ingredientKey);

            if (fridgeIngredient != null) {
                if (fridgeIngredient.getQuantity() >= requiredQuantity) {
                    // Dacă cantitatea din frigider este suficientă, eliminăm ingredientul din lista de cumpărături
                    entry.setValue(0);
                } else {
                    // Dacă cantitatea din frigider nu este suficientă, calculăm diferența și o păstrăm în lista de cumpărături
                    shoppingList.put(ingredientKey, (int) (requiredQuantity - fridgeIngredient.getQuantity()));
                }
            }
        }

        // Eliminăm ingredientele care au fost marcate cu 0
        shoppingList.entrySet().removeIf(entry -> entry.getValue() == 0);

        saveUserCartInDataBase(shoppingList, userDto);
    }
    private UserIngredientDto findIngredientInFridge(List<UserIngredientDto> fridgeUserIngredients, IngredientKey ingredientKey) {
        for (UserIngredientDto fridgeIngredient : fridgeUserIngredients) {
            if (fridgeIngredient.getIngredient().getId().equals(ingredientKey.getIngredientId())
                    && fridgeIngredient.getUnitOfMeasure().equals(ingredientKey.getUnitOfMeasure())) {
                return fridgeIngredient;
            }
        }
        return null;
    }


    public void clearPreviousShoppingList(Integer userId) {
        userIngredientRepository.removeUserIngredientEntitiesByIsCartIngredientAndUserId(true, userId);
    }

    public void saveUserCartInDataBase(Map<IngredientKey, Integer> shoppingList, UserDto userDto) {
        // Sterge ce lista anterioara de cumparaturi
        clearPreviousShoppingList(userDto.getId());

        // Adauga noua lista de cumparaturi
        for (Map.Entry<IngredientKey, Integer> entry : shoppingList.entrySet()) {
            IngredientKey ingredientKey = entry.getKey();
            IngredientDto ingredient = ingredientRepository.findById(ingredientKey.getIngredientId()).map(IngredientDto::fromEntity).orElseThrow(NotFoundException::new);

            double quantity = entry.getValue();

            UserIngredientEntity userIngredientEntity = new UserIngredientEntity();
            userIngredientEntity.setQuantity(quantity);
            userIngredientEntity.setIsCartIngredient(true);
            userIngredientEntity.setUnitOfMeasure(ingredientKey.getUnitOfMeasure());
            userIngredientEntity.setUser(userRepository.findById(userDto.getId()).orElseThrow(NotFoundException::new));
            userIngredientEntity.setIngredient(ingredientRepository.findById(ingredient.getId()).orElseThrow(NotFoundException::new));

            userIngredientRepository.save(userIngredientEntity);
        }

    }

    public void saveUserRecommendations(Map<String, List<RecipeDto>> recommendationsMap, Integer userId) {
        for (Map.Entry<String, List<RecipeDto>> entry : recommendationsMap.entrySet()) {
            String dateStr = entry.getKey();
            List<RecipeDto> recipes = entry.getValue();

            Integer breakfastRecipeId = getRecipeId(recipes, 0);
            Integer lunchRecipeId = getRecipeId(recipes, 1);
            Integer dinnerRecipeId = getRecipeId(recipes, 2);

            LocalDateTime dateTime = LocalDateTime.parse(dateStr + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);

            Optional<UserRecommendationEntity> existingRecommendation = userRecommendationsRepository.findByDateTimeIsAndUserId(dateTime, userId);
            if (existingRecommendation.isPresent()) {
                UserRecommendationEntity recommendationEntity = existingRecommendation.get();
                recommendationEntity.setBreakfastRecipeId(recipeRepository.findById(breakfastRecipeId)
                        .orElseThrow(() -> new NotFoundException(String.format(RECIPE_NOT_FOUND, breakfastRecipeId))));
                recommendationEntity.setLunchRecipeId(recipeRepository.findById(lunchRecipeId)
                        .orElseThrow(() -> new NotFoundException(String.format(RECIPE_NOT_FOUND, lunchRecipeId))));
                recommendationEntity.setDinnerRecipeId(recipeRepository.findById(dinnerRecipeId)
                        .orElseThrow(() -> new NotFoundException(String.format(RECIPE_NOT_FOUND, dinnerRecipeId))));
                userRecommendationsRepository.save(recommendationEntity);
            } else {
                UserRecommendationEntity recommendationEntity = new UserRecommendationEntity();
                recommendationEntity.setDateTime(dateTime);
                recommendationEntity.setBreakfastRecipeId(recipeRepository.findById(breakfastRecipeId)
                        .orElseThrow(() -> new NotFoundException(String.format(RECIPE_NOT_FOUND, breakfastRecipeId))));
                recommendationEntity.setLunchRecipeId(recipeRepository.findById(lunchRecipeId)
                        .orElseThrow(() -> new NotFoundException(String.format(RECIPE_NOT_FOUND, lunchRecipeId))));
                recommendationEntity.setDinnerRecipeId(recipeRepository.findById(dinnerRecipeId)
                        .orElseThrow(() -> new NotFoundException(String.format(RECIPE_NOT_FOUND, dinnerRecipeId))));
                recommendationEntity.setUser(userRepository.findById(userId).orElseThrow(NotFoundException::new));
                userRecommendationsRepository.save(recommendationEntity);
            }

        }
    }

    public int calculateRecipeScore(UserDto userDto, RecipeDto recipeDto) {
        int score = 0;

        // Definim ponderile pentru fiecare criteriu
        Map<String, Integer> weights = new HashMap<>();
        weights.put("disliked_ingredients", -3);
        weights.put("fridge_ingredients", 2);
        weights.put("user_diets", 3);
        weights.put("user_cuisines", 3);
        weights.put("cooking_level", 1);
        weights.put("want_to_try_new_cuisines", 2);
        weights.put("want_to_learn_new_skills", 2);


        // 1. Micsoram scorul retetelor ce contin ingredientele neplăcute
        List<IngredientDto> dislikedIngredientsIds = userDto.getUserDislikedIngredients();
        List<Integer> recipeIngredientsIds = new ArrayList<>();
        for (RecipeIngredientDto ingredient : recipeDto.getIngredientDtoList()) {
            recipeIngredientsIds.add(ingredient.getIngredient().getId());
        }

        if (recipeIngredientsIds.stream().anyMatch(dislikedIngredientsIds::contains)) {
            score += weights.get("disliked_ingredients");
        }

        // 2. Prioritizăm ingredientele disponibile în frigider
        List<UserIngredientDto> fridgeUserIngredients = userDto.getUserIngredients()
                .stream()
                .filter(ingredient -> !ingredient.getIsCartIngredient())
                .toList();

        List<Integer> fridgeIngredientIds = new ArrayList<>();
        for (UserIngredientDto ingredient : fridgeUserIngredients) {
            fridgeIngredientIds.add(ingredient.getIngredient().getId());
        }

        for (UserIngredientDto ingredient : fridgeUserIngredients) {
            if (recipeIngredientsIds.contains(ingredient.getIngredient().getId()) && Boolean.TRUE.equals(!ingredient.getIsCartIngredient())) {
                score += weights.get("fridge_ingredients");
            }
        }

        // 3. Dacă rețeta respectă dieta utilizatorului, adăugăm puncte
        List<DietDto> userDiets = userDto.getUserDiets();
        List<String> recipeDiets = new ArrayList<>();
        for (DietDto diet : recipeDto.getRecipeDiets()) {
            recipeDiets.add(diet.getName());
        }

        if (recipeDiets.stream().anyMatch(diet -> userDiets.stream().anyMatch(userDiet -> userDiet.getName().equals(diet)))) {
            score += weights.get("user_diets");
        }

        // 4. Verificăm dacă bucătăria rețetei se regăsește în preferințele utilizatorului
        List<String> userCuisines = new ArrayList<>();
        for (CuisineDto cuisine : userDto.getUserCuisines()) {
            userCuisines.add(cuisine.getName());
        }

        if (userCuisines.contains(recipeDto.getCuisineName())) {
            score += weights.get("user_cuisines");
        }

        // 5. Verificăm dacă nivelul de dificultate a retetei este acelasi cu niveul userului
        CookingLevel userCookingLevel = userDto.getCookingLevel();
        if (recipeDto.getCookingLevel().equals(userCookingLevel)) {
            score += weights.get("cooking_level");
        }

        // 6. Verificam daca utilizatorul vrea sa incerce noi bucatarii iar bucataria retetei nu se regaseste in preferintele utilizatorului

        if (Boolean.TRUE.equals(userDto.getWantToTryNewCuisines()) && !userCuisines.contains(recipeDto.getCuisineName())) {
            score += weights.get("want_to_try_new_cuisines");
        }

        // 7. Verificam daca utilizatorul vrea sa invete noi skill-uri iar nivelul de dificultate al retetei este mai mare decat nivelul de dificultate al utilizatorului

        if (Boolean.TRUE.equals(userDto.getWantToLearnNewSkills()) && recipeDto.getCookingLevel().ordinal() > userCookingLevel.ordinal()) {
            score += weights.get("want_to_learn_new_skills");
        }
        return score;
    }

    public Map<RecipeDto, Integer> rankRecipes(UserDto userDto, List<RecipeDto> recipeDtos) {
        Map<RecipeDto, Integer> recipeScores = new HashMap<>();

        for (RecipeDto recipeDto : recipeDtos) {
            int score = calculateRecipeScore(userDto, recipeDto);
            recipeScores.put(recipeDto, score);
        }

        List<Map.Entry<RecipeDto, Integer>> sortedEntries = new ArrayList<>(recipeScores.entrySet());
        sortedEntries.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        Map<RecipeDto, Integer> sortedRecipes = new LinkedHashMap<>();
        for (Map.Entry<RecipeDto, Integer> entry : sortedEntries) {
            sortedRecipes.put(entry.getKey(), entry.getValue());
        }

        return sortedRecipes;
    }

    public Map<String, List<RecipeDto>> getRecommendations(UserDto userDto, List<RecipeDto> recipeDtos, LocalDateTime startDate, LocalDateTime endDate, int numRecipesPerDay) {
        Map<String, List<RecipeDto>> recommendations = new HashMap<>();
        Map<RecipeDto, Integer> sortedRecipesDto = rankRecipes(userDto, recipeDtos);

        LocalDate currentDate = startDate.toLocalDate();
        double dailyCaloriesLimit = userDto.getBms();

        while (!currentDate.isAfter(endDate.toLocalDate())) {
            recommendations.put(currentDate.format(DateTimeFormatter.ISO_LOCAL_DATE), new ArrayList<>());
            double dailyCaloriesConsumed = 0;

            for (int i = 0; i < numRecipesPerDay; i++) {
                if (dailyCaloriesConsumed < dailyCaloriesLimit) {
                    if (!sortedRecipesDto.isEmpty()) {
                        RecipeDto recommendedRecipe = sortedRecipesDto.entrySet().iterator().next().getKey();
                        List<RecipeDto> sameScoreRecipes = new ArrayList<>();
                        int recommendedRecipeScore = sortedRecipesDto.get(recommendedRecipe);

                        for (Map.Entry<RecipeDto, Integer> entry : sortedRecipesDto.entrySet()) {
                            if (entry.getValue() == recommendedRecipeScore) {
                                sameScoreRecipes.add(entry.getKey());
                            }
                        }


                        // Criteriu de departajare in cazul in care doua retete au acelasi scor - timp de preparare sau numar de calorii
                        if (Boolean.TRUE.equals(userDto.getWantToSaveTime())) {
                            sameScoreRecipes.sort(Comparator.comparingInt(RecipeDto::getPrepTime));
                        } else {
                            sameScoreRecipes.sort(Comparator.comparingInt(RecipeDto::getKcals));
                        }

                        recommendations.get(currentDate.format(DateTimeFormatter.ISO_LOCAL_DATE)).add(sameScoreRecipes.get(0));

                        sortedRecipesDto.remove(sameScoreRecipes.get(0));

                        dailyCaloriesConsumed += recommendedRecipe.getKcals();
                    } else {
                        break; // Ne oprim dacă nu mai avem rețete disponibile
                    }
                } else {
                    break; // Ne oprim dacă am atins limita de calorii pe zi
                }
            }

            currentDate = currentDate.plusDays(1);
        }
        return recommendations;
    }

}
