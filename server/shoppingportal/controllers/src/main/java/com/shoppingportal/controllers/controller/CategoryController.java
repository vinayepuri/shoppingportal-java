package com.ecommerce.controllers.controller;

import com.ecommerce.controllers.mappers.ApparelCategoryControllerMapper;
import com.ecommerce.controllers.dto.category.ApparelCategoriesResponse;
import com.ecommerce.controllers.dto.category.BrandCategoriesResponse;
import com.ecommerce.controllers.handler.ApparelCategoryHandler;
import com.ecommerce.controllers.handler.BrandCategoryHandler;
import com.ecommerce.controllers.mappers.BrandCategoryControllerMapper;
import com.ecommerce.domain.model.category.ApparelCategoriesDomainResponse;
import com.ecommerce.domain.model.category.BrandCategoriesDomainResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final ApparelCategoryHandler apparelCategoryDriverPort;
    private final BrandCategoryHandler brandCategoryDriverPort;
    private final BrandCategoryControllerMapper brandCategoryControllerMapper;
    private final ApparelCategoryControllerMapper apparelCategoryConverter;

    public CategoryController(ApparelCategoryHandler apparelCategoryDriverPort, BrandCategoryHandler brandCategoryDriverPort, BrandCategoryControllerMapper brandCategoryControllerMapper, ApparelCategoryControllerMapper apparelCategoryConverter) {
        this.apparelCategoryDriverPort = apparelCategoryDriverPort;
        this.brandCategoryDriverPort = brandCategoryDriverPort;
        this.brandCategoryControllerMapper = brandCategoryControllerMapper;
        this.apparelCategoryConverter = apparelCategoryConverter;
    }

    @GetMapping("/brands")
    public ResponseEntity<BrandCategoriesResponse> getBrandCategoriesByGenderIdAndApparelCategoryId(@RequestParam(required = true) int genderId,
                                                                                                          @RequestParam(defaultValue = "0") int apparelCategoryId) {

        BrandCategoriesDomainResponse brandCategoriesDomainResponse = brandCategoryDriverPort.getBrandCategoriesByGenderIdAndApparelCategoryId(genderId, apparelCategoryId);
        BrandCategoriesResponse dto = brandCategoryControllerMapper.toBrandCategoriesResponse(brandCategoriesDomainResponse);

            return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/apparels")
    public ResponseEntity<ApparelCategoriesResponse> getApparelCategoriesByGenderIdAndBranCategoryId(@RequestParam(required = true) int genderId,
                                                                                                           @RequestParam(defaultValue = "0") int brandId) {
        ApparelCategoriesDomainResponse apparelCategoriesDomainResponse = apparelCategoryDriverPort.getApparelCategoriesByGenderIdAndBranCategoryId(genderId, brandId);
        ApparelCategoriesResponse dto = apparelCategoryConverter.toApparelCategoriesResponse(apparelCategoriesDomainResponse);

            return new ResponseEntity<>(dto, HttpStatus.OK);

    }

}
