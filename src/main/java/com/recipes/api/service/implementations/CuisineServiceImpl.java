package com.recipes.api.service.implementations;

import com.recipes.api.dtos.CuisineDto;
import com.recipes.api.entity.CuisineEntity;
import com.recipes.api.exceptions.NotFoundException;
import com.recipes.api.repository.CuisineRepository;
import com.recipes.api.service.interfaces.CuisineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.recipes.api.common.Constants.CUISINE_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Log
public class CuisineServiceImpl implements CuisineService {

    private final CuisineRepository cuisineRepository;

    @Override
    public List<CuisineDto> getCuisines() {
        return cuisineRepository.findAll().stream().map(CuisineDto::fromEntity).toList();
    }

    @Override
    public CuisineDto getCuisineById(Integer id) {
        return cuisineRepository
                .findById(id)
                .map(CuisineDto::fromEntity)
                .orElseThrow(() -> new NotFoundException(String.format(CUISINE_NOT_FOUND, id)));
    }


    @Override
    public CuisineDto addCuisine(CuisineDto cuisineDto) {
        CuisineEntity cuisineEntity = CuisineEntity.builder()
                .id(cuisineDto.getId())
                .name(cuisineDto.getName())
                .build();
        return CuisineDto.fromEntity(cuisineRepository.save(cuisineEntity));
    }

    @Override
    public CuisineDto editCuisine(CuisineDto cuisineDto) {
        CuisineEntity cuisineEntity = CuisineEntity.builder()
                .id(cuisineDto.getId())
                .name(cuisineDto.getName())
                .build();
        return CuisineDto.fromEntity(cuisineRepository.save(cuisineEntity));
    }
}
