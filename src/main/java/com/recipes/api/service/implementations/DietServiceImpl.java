package com.recipes.api.service.implementations;

import com.recipes.api.dtos.CuisineDto;
import com.recipes.api.dtos.DietDto;
import com.recipes.api.repository.CuisineRepository;
import com.recipes.api.repository.DietRepository;
import com.recipes.api.service.interfaces.CuisineService;
import com.recipes.api.service.interfaces.DietService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log
public class DietServiceImpl implements DietService {
    private final DietRepository dietRepository;

    @Override
    public List<DietDto> getDiets() {
        return dietRepository.findAll().stream().map(DietDto::fromEntity).toList();

    }

    @Override
    public DietDto getDietById(Integer id) {
        return dietRepository.findById(id).map(DietDto::fromEntity).get();
    }
}
