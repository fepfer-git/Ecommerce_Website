package com.example.ecommerce_website.controller;

import com.example.ecommerce_website.dto.request.CategoryDtoRequest;
import com.example.ecommerce_website.dto.response.CategoryDtoResponse;
import com.example.ecommerce_website.entity.Category;
import com.example.ecommerce_website.services.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("categories")
    public List<Category> getCategories(){
        return categoryService.getListCategories();
    }

    @PostMapping("/category")
    public CategoryDtoResponse createNewCategory(@Valid @RequestBody CategoryDtoRequest categoryDtoRequest){
        return categoryService.createNewCategory(categoryDtoRequest);
    }

    @PutMapping("/category")
    public CategoryDtoResponse updateACategory(@Valid @RequestBody CategoryDtoRequest categoryDtoRequest){
        return categoryService.updateACategory(categoryDtoRequest);
    }

    @DeleteMapping(path = "category/{categoryId}")
    public ResponseEntity<String> deleteACategory(@PathVariable("categoryId") int id){
        categoryService.deleteACategory(id);
        return ResponseEntity.ok().body("Delete category with id: "+ id+" successfully!");
    }



}
