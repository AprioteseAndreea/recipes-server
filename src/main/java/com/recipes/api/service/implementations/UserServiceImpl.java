package com.recipes.api.service.implementations;

import com.recipes.api.dtos.UserDto;
import com.recipes.api.dtos.UserRecommendationDto;
import com.recipes.api.exceptions.NotFoundException;
import com.recipes.api.repository.UserRepository;
import com.recipes.api.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.recipes.api.common.Constants.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Log
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDto getUserById(Integer id) {
        return userRepository
                .findById(id)
                .map(UserDto::fromEntity)
                .orElseThrow(() -> new NotFoundException(String.format(USER_NOT_FOUND, id)));
    }

    @Override
    public UserDto getUserByEmail(String email) {
        return UserDto.fromEntity(userRepository.findByEmailIs(email));
    }

}
