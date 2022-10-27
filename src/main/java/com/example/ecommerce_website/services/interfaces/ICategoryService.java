package com.example.ecommerce_website.services.interfaces;
import com.example.ecommerce_website.entity.Category;

import java.util.List;
import java.util.Optional;


public interface ICategoryService {
    public List<Category> getListCategories();

    void createNewCategory(Category category);

    void updateACategory(Category category);

    void deleteACategory(int id);
    Optional <Category> getACategory(int categoryId);
}
