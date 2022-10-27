package com.example.ecommerce_website.services.interfaces;
import com.example.ecommerce_website.entity.Category;

import java.util.List;


public interface ICategoryService {
    public List<Category> getListCategories();

    void createNewCategory(Category category);

    void updateACategory(Category category);

    void deleteACategory(int id);
}
