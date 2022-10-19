package com.example.ecommerce_website.controller;

import com.example.ecommerce_website.entity.Category;
import com.example.ecommerce_website.services.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("all")
    public List<Category> getCategories(){
        return categoryService.getListCategories();
    }

    @PostMapping("create")
    public void createNewCategory(@RequestBody Category category){
        categoryService.createNewCategory(category);
    }

    @PutMapping("update")
    public void updateACategory(@RequestBody Category category){
        categoryService.updateACategory(category);
    }

    @DeleteMapping(path = "delete/{categoryId}")
    public void deleteACategory(@PathVariable("categoryId") int id){
        categoryService.deleteACategory(id);
    }



}
