package com.example.ecommerce_website.services.impl;

import com.example.ecommerce_website.dto.create.ProductDto;
import com.example.ecommerce_website.dto.request.ProductDetailDtoRequest;
import com.example.ecommerce_website.dto.request.ProductDtoRequest;
import com.example.ecommerce_website.entity.Category;
import com.example.ecommerce_website.entity.Product;
import com.example.ecommerce_website.dto.response.ProductDtoResponse;
import com.example.ecommerce_website.entity.ProductDetail;
import com.example.ecommerce_website.exception.NotFoundException;
import com.example.ecommerce_website.mappers.ObjectMapperUtils;
import com.example.ecommerce_website.repository.CategoryRepository;
import com.example.ecommerce_website.repository.ProductRepository;
import com.example.ecommerce_website.services.interfaces.IProductDetailService;
import com.example.ecommerce_website.services.interfaces.IProductService;
import org.modelmapper.internal.asm.commons.JSRInlinerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ObjectMapperUtils objectMapperUtils;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private IProductDetailService productDetailService;
    @Override
    public List<ProductDtoResponse> getListProducts() {
        List<Product> productList = productRepository.findAll();
        return objectMapperUtils.mapAll(productList,ProductDtoResponse.class);
    }

    @Override
    public List<ProductDtoResponse> getListProductsAvailable() {
        List<Product> productList = productRepository.findProductsByStatus("Active");
        return objectMapperUtils.mapAll(productList,ProductDtoResponse.class);
    }

    @Override
    public ProductDtoResponse createNewProduct(ProductDtoRequest product) {
        Optional <Category> category = categoryRepository.findById(product.getCategoryId());
        if(category.isEmpty()){
            throw new NotFoundException("Category not found!");
        }

        Product productSave = Product.builder().productDescription(product.getProductDescription())
                .category(category.get())
                .productName(product.getProductName())
                .status("Active")
                .build();
        ProductDtoResponse savedProduct = objectMapperUtils.map(productRepository.save(productSave), ProductDtoResponse.class);

        for (ProductDetailDtoRequest productDetailDtoRequest: product.getProductDetails()) {
            productDetailDtoRequest.setProduct(productSave);
            productDetailService.addNewProductDetail(productDetailDtoRequest);
        }
        return savedProduct;
    }

    //fix
    @Override
    public ProductDtoResponse updateAProduct(ProductDto productDtoUpdate) {
        Product updateProduct = productRepository.findById(productDtoUpdate.getProductId()).get();
        Optional <Category> category = categoryRepository.findById(productDtoUpdate.getCategoryId());
        if (updateProduct == null){
            throw new NotFoundException("Product not found!");
        } else if (category.isEmpty()) {
            throw new NotFoundException("Category not found!");
        }
        updateProduct.setProductName(productDtoUpdate.getProductName());
        updateProduct.setProductDescription(productDtoUpdate.getProductDescription());
        updateProduct.setCategory(categoryRepository.findById(productDtoUpdate.getCategoryId()).get());
        productRepository.save(updateProduct);
        return objectMapperUtils.map(updateProduct,ProductDtoResponse.class);
    }

    @Override
    public ProductDtoResponse deleteAProduct(int id) {
        Product product = productRepository.getById(id);
        if(product == null){
            throw new NotFoundException("Product not found!");
        }
        product.setStatus("Inactive");
        Product deletedProduct = productRepository.save(product);
        return objectMapperUtils.map(deletedProduct, ProductDtoResponse.class);
    }

    @Override
    public List<ProductDtoResponse> getAllProductsByCategoryId(int id) {
        List<Product> products = productRepository.findProductsByCategory_CategoryId(id);
        if (products.isEmpty()) {
            throw new NotFoundException("There are no products in this category!");
        }
        return objectMapperUtils.mapAll(products, ProductDtoResponse.class);

    }

    @Override
    public ProductDtoResponse getProductById(int id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new NotFoundException("There is not any product with id: " + id);
        }
        return objectMapperUtils.map(product.get(), ProductDtoResponse.class);
    }

    @Override
    public List<ProductDtoResponse> getProductByName(String searchName) {
        List<Product> products = productRepository.findProductsByProductNameContainingIgnoreCase(searchName);
        if (products.isEmpty()) {
            throw new NotFoundException("There are no product with the name: " + searchName);
        } else {
            return objectMapperUtils.mapAll(products, ProductDtoResponse.class);
        }
    }


}


