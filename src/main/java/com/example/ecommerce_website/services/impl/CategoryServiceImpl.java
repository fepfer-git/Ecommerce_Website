package com.example.ecommerce_website.services.impl;

import com.example.ecommerce_website.dto.request.CategoryDtoRequest;
import com.example.ecommerce_website.dto.response.CategoryDtoResponse;
import com.example.ecommerce_website.entity.Category;
import com.example.ecommerce_website.exception.BadRequestException;
import com.example.ecommerce_website.exception.DuplicatedException;
import com.example.ecommerce_website.exception.NotFoundException;
import com.example.ecommerce_website.mappers.ObjectMapperUtils;
import com.example.ecommerce_website.repository.CategoryRepository;
import com.example.ecommerce_website.repository.ProductRepository;
import com.example.ecommerce_website.services.interfaces.ICategoryService;
import com.example.ecommerce_website.services.interfaces.IProductService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Builder
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ObjectMapperUtils objectMapperUtils;
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Category> getListCategories() {
        return categoryRepository.findCategoriesByStatus("Active");
    }

    @Override
    public CategoryDtoResponse createNewCategory(CategoryDtoRequest categoryDtoRequest) {
        String active = "Active";
        Category foundCategory = categoryRepository.findCategoryByCategoryNameIgnoreCase(categoryDtoRequest.getCategoryName());
        Category savedCategory;
        if (foundCategory != null && "Active".equals(foundCategory.getStatus())){
            throw new DuplicatedException("Category name is already exist!");
        }
        if(foundCategory != null && !"Active".equals(foundCategory.getStatus())){
            foundCategory.setStatus("Active");
            savedCategory = foundCategory;
        }else{
            savedCategory = Category.builder().categoryName(categoryDtoRequest.getCategoryName()).status(active).build();
        }

        return objectMapperUtils.map(categoryRepository.save(savedCategory), CategoryDtoResponse.class);
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
        if(productRepository.findProductsByCategory_CategoryId(id).size() > 0){
            throw new BadRequestException("Still have product belong to this category. Delete fail!");
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
