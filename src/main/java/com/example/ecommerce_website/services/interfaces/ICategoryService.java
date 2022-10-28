package com.example.ecommerce_website.services.interfaces;
import com.example.ecommerce_website.dto.request.CategoryDtoRequest;
import com.example.ecommerce_website.dto.response.CategoryDtoResponse;
import com.example.ecommerce_website.entity.Category;

import java.util.List;
import java.util.Optional;


public interface ICategoryService {
    public List<Category> getListCategories();

    CategoryDtoResponse createNewCategory(CategoryDtoRequest categoryDtoRequest);

    CategoryDtoResponse updateACategory(CategoryDtoRequest categoryDtoRequest);

    void deleteACategory(int id);
    Optional <Category> getACategory(int categoryId);
}
