package com.example.ecommerce_website.services.impl;

import com.example.ecommerce_website.dto.ProductDto;
import com.example.ecommerce_website.dto.update.ProductDtoUpdate;
import com.example.ecommerce_website.entity.Product;
import com.example.ecommerce_website.exception.NotFoundException;
import com.example.ecommerce_website.services.details.repository.ProductRepository;
import com.example.ecommerce_website.services.interfaces.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Product> getListProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getListProductsAvailable() {
        return productRepository.findProductsByStatus("Active");
    }

    @Override
    public void createNewProduct(Product product) {
        product.setStatus("Active");
        productRepository.save(product);
    }

    //fix
    @Override
    public Product updateAProduct(ProductDtoUpdate productDtoUpdate) {
        Product updateProduct = productRepository.findById(productDtoUpdate.getProductId()).get();
        Product product = modelMapper.map(productDtoUpdate, Product.class);

        updateProduct.setProductName(product.getProductName());
        updateProduct.setCategory(product.getCategory());
        updateProduct.setImages(product.getImages());
        updateProduct.setProductDescription(product.getProductDescription());

        return productRepository.save(updateProduct);
    }

    @Override
    public void deleteAProduct(int id) {
        Product product = productRepository.getById(id);
        product.setStatus("Inactive");
        productRepository.save(product);
    }

    @Override
    public List<Product> getAllProductsByCategoryId(int id) {
        List<Product> products = productRepository.findProductsByCategory_CategoryId(id);
        if(products.isEmpty()){
            throw new NotFoundException("There are no products in this category!");
        }
        return products;

    }

    @Override
    public ProductDto getProductById(int id) {
            Optional<Product> product = productRepository.findById(id);
            try {
                return modelMapper.map(product.get(), ProductDto.class);
            }catch(NoSuchElementException e){
                throw new NotFoundException("There is not any product with id: " + id);
            }
    }

    @Override
    public List<Product> getProductByName(String searchName) {
        List<Product> products = productRepository.findProductsByProductNameContainingIgnoreCase(searchName);
        if (products.isEmpty()){
            throw new NotFoundException("There are no product with the name: " + searchName);
        }else{
            return products;
        }
    }


}


