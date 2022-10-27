package com.example.ecommerce_website.services.impl;

import com.example.ecommerce_website.entity.Category;
import com.example.ecommerce_website.repository.CategoryRepository;
import com.example.ecommerce_website.services.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getListCategories() {
        return categoryRepository.findCategoriesByStatus("Active");
    }

    @Override
    public void createNewCategory(Category category) {
        category.setStatus("Active");
        categoryRepository.save(category);
    }

    @Override
    public void updateACategory(Category category) {
        categoryRepository.findById(category.getCategoryId()).get();
        Category updateCategory = new Category();
        updateCategory.setCategoryName(category.getCategoryName());
        categoryRepository.save(updateCategory);
    }

    @Override
    public void deleteACategory(int id) {
        Category category = categoryRepository.getById(id);
        category.setStatus("Inactive");
        categoryRepository.save(category);
    }

    @Override
    public Optional <Category> getACategory(int categoryId) {
        Optional <Category> category = categoryRepository.findById(categoryId);
        return category;
    }

}
