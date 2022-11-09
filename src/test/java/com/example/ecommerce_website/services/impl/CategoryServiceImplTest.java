package com.example.ecommerce_website.services.impl;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.example.ecommerce_website.dto.request.CategoryDtoRequest;
import com.example.ecommerce_website.entity.Category;
import com.example.ecommerce_website.entity.Size;
import com.example.ecommerce_website.exception.DuplicatedException;
import com.example.ecommerce_website.exception.NotFoundException;
import com.example.ecommerce_website.mappers.ObjectMapperUtils;
import com.example.ecommerce_website.repository.CategoryRepository;
import com.example.ecommerce_website.repository.ProductDetailRepository;
import com.example.ecommerce_website.repository.ProductRepository;
import com.example.ecommerce_website.repository.SizeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CategoryServiceImplTest {

    private CategoryRepository categoryRepository;
    private ObjectMapperUtils objectMapperUtils;
    private ProductRepository productRepository;
    private CategoryServiceImpl categoryService;
    private Category category;
    private CategoryDtoRequest categoryDtoRequest;
    @BeforeEach
    void beforeEach() {
        categoryRepository = mock(CategoryRepository.class);
        objectMapperUtils = mock(ObjectMapperUtils.class);
        productRepository = mock(ProductRepository.class);
        categoryService = CategoryServiceImpl.builder()
                .categoryRepository(categoryRepository)
                .productRepository(productRepository)
                .objectMapperUtils(objectMapperUtils)
                .build();
        category = mock(Category.class);
        categoryDtoRequest = mock(CategoryDtoRequest.class);
    }


    @Test
    void createNewCategory_WhenCategoryNameIsAlreadyExist() {
        DuplicatedException thrown = assertThrows(DuplicatedException.class,
                () -> {
                    when(categoryRepository.findCategoryByCategoryNameIgnoreCase(categoryDtoRequest.getCategoryName())).thenReturn(category);
                    when(category.getStatus()).thenReturn("Active");
                    categoryService.createNewCategory(categoryDtoRequest);
                });
        Assertions.assertEquals("Category name is already exist!", thrown.getMessage());

    }

    @Test
    void updateACategory_WhenCategoryNotFound() {
        NotFoundException thrown = assertThrows(NotFoundException.class,
                () -> {
                    when(categoryRepository.findById(categoryDtoRequest.getCategoryId())).thenReturn(Optional.empty());
                    categoryService.updateACategory(categoryDtoRequest);
                });
        Assertions.assertEquals("Category doesn't exist!", thrown.getMessage());
    }

//    @Test
//    void updateACategory_WhenNameIsDuplicate() {
//
//        DuplicatedException thrown = assertThrows(DuplicatedException.class,
//                () -> {
//                    when(categoryRepository.findById(categoryDtoRequest.getCategoryId())).thenReturn(Optional.of(category));
//                    when(Optional.of(category).get().getCategoryName()).thenReturn(categoryDtoRequest.getCategoryName());
//                    categoryService.updateACategory(categoryDtoRequest);
//                });
//        Assertions.assertEquals("Category name is already exist!", thrown.getMessage());
//    }


    @Test
    void deleteACategory_WhenCategoryNotFound() {
        NotFoundException thrown = assertThrows(NotFoundException.class,
                () -> {
                    when(categoryRepository.findById(categoryDtoRequest.getCategoryId())).thenReturn(Optional.empty());
                    categoryService.deleteACategory(categoryDtoRequest.getCategoryId());
                });
        Assertions.assertEquals("Category doesn't exist!", thrown.getMessage());
    }

}