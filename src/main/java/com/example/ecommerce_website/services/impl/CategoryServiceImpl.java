package com.example.ecommerce_website.services.impl;

import com.example.ecommerce_website.dto.request.CategoryDtoRequest;
import com.example.ecommerce_website.dto.response.CategoryDtoResponse;
import com.example.ecommerce_website.entity.Category;
import com.example.ecommerce_website.exception.DuplicatedException;
import com.example.ecommerce_website.exception.NotFoundException;
import com.example.ecommerce_website.mappers.ObjectMapperUtils;
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
    @Autowired
    private ObjectMapperUtils objectMapperUtils;
    @Override
    public List<Category> getListCategories() {
        return categoryRepository.findCategoriesByStatus("Active");
    }

    @Override
    public CategoryDtoResponse createNewCategory(CategoryDtoRequest categoryDtoRequest) {
        String active = "Active";
        if (categoryRepository.findCategoryByCategoryNameIgnoreCase(categoryDtoRequest.getCategoryName()) != null){
            throw new DuplicatedException("Category name is already exist!");
        }
        Category category = Category.builder().categoryName(categoryDtoRequest.getCategoryName()).status(active).build();

        return objectMapperUtils.map(categoryRepository.save(category), CategoryDtoResponse.class);
    }

    @Override
    public CategoryDtoResponse updateACategory(CategoryDtoRequest categoryDtoRequest) {
        Optional<Category> updateCategory = categoryRepository.findById(categoryDtoRequest.getCategoryId());
        if(updateCategory.isEmpty()){
            throw new NotFoundException("Category doesn't exist!");
        } else if (updateCategory.get().getCategoryName().equals(categoryDtoRequest.getCategoryName())) {
            throw new DuplicatedException("Category name is already exist!");
        }
        updateCategory.get().setCategoryName(categoryDtoRequest.getCategoryName());
        updateCategory.get().setStatus(categoryDtoRequest.getStatus());
        return objectMapperUtils.map(categoryRepository.save(updateCategory.get()),CategoryDtoResponse.class);
    }

    @Override
    public void deleteACategory(int id) {
        Optional<Category> deleteCategory = categoryRepository.findById(id);
        if(deleteCategory.isEmpty()){
            throw new NotFoundException("Category doesn't exist!");
        }
        deleteCategory.get().setStatus("Inactive");
        categoryRepository.save(deleteCategory.get());
    }

    @Override
    public Optional <Category> getACategory(int categoryId) {
        Optional <Category> category = categoryRepository.findById(categoryId);
        return category;
    }

}
