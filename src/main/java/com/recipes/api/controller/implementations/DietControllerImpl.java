package com.recipes.api.controller.implementations;

import com.recipes.api.controller.interfaces.DietController;
import com.recipes.api.dtos.DietDto;
import com.recipes.api.service.interfaces.DietService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/diets")
@RequiredArgsConstructor
public class DietControllerImpl implements DietController {
    private final DietService dietService;

    @Override
    public ResponseEntity<List<DietDto>> getDiets() {
        List<DietDto> dietDtos = dietService.getDiets();
        HttpStatus status = ObjectUtils.isNotEmpty(dietDtos) ? HttpStatus.OK : HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(dietDtos, status);
    }

    @Override
    public ResponseEntity<DietDto> getDietById(Integer id) {
        DietDto dietDto = dietService.getDietById(id);
        HttpStatus status = ObjectUtils.isNotEmpty(dietDto) ? HttpStatus.OK : HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(dietDto, status);
    }
}
