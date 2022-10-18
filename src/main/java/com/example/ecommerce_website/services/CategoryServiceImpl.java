package com.example.ecommerce_website.services;

import com.example.ecommerce_website.entity.Category;
import com.example.ecommerce_website.repository.CategoryRepository;
import com.example.ecommerce_website.services.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getListCategories() {
        return categoryRepository.findCategoriesByStatus("Active");
    }

    @Override
    public void createNewCategory(Category category) {
        category.setStatus("Active");
        Category saveCategory = categoryRepository.save(category);
    }

    @Override
    public void updateACategory(Category category) {
        Category updateCategory = categoryRepository.save(category);
    }

    @Override
    public void deleteACategory(int id) {
        Category category = categoryRepository.getById(id);
        category.setStatus("Inactive");
        Category updateCategoryStatus = categoryRepository.save(category);
    }



}
