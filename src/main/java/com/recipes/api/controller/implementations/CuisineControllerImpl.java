package com.recipes.api.controller.implementations;

import com.recipes.api.controller.interfaces.CuisineController;
import com.recipes.api.dtos.CuisineDto;
import com.recipes.api.service.interfaces.CuisineService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cuisines")
@RequiredArgsConstructor
public class CuisineControllerImpl implements CuisineController {

    private final CuisineService cuisineService;
    @Override
    public ResponseEntity<List<CuisineDto>> getCuisines() {
        List<CuisineDto> cuisineDtos = cuisineService.getCuisines();
        HttpStatus status = ObjectUtils.isNotEmpty(cuisineDtos) ? HttpStatus.OK : HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(cuisineDtos, status);
    }

    @Override
    public ResponseEntity<CuisineDto> getCuisineById(Integer id) {
        CuisineDto cuisineDto = cuisineService.getCuisineById(id);
        HttpStatus status = ObjectUtils.isNotEmpty(cuisineDto) ? HttpStatus.OK : HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(cuisineDto, status);
    }

    @Override
    public ResponseEntity<CuisineDto> addCuisine(CuisineDto cuisineDto){
        cuisineDto.setId(null);
        return new ResponseEntity<>(cuisineService.addCuisine(cuisineDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CuisineDto> editCuisine(CuisineDto cuisineDto) {
        return new ResponseEntity<>(cuisineService.editCuisine(cuisineDto), HttpStatus.OK);
    }
}
