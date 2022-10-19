package com.example.ecommerce_website.services;

import com.example.ecommerce_website.dto.ProductDto;
import com.example.ecommerce_website.entity.Category;
import com.example.ecommerce_website.entity.Product;
import com.example.ecommerce_website.exception.NotFoundException;
import com.example.ecommerce_website.repository.ProductRepository;
import com.example.ecommerce_website.services.interfaces.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void updateAProduct(Product product) {
        Product updateProduct = productRepository.findById(product.getProductId()).get();
        updateProduct.setProductDescription(product.getProductDescription());
        updateProduct.setProductName(product.getProductName());
        updateProduct.setStatus(product.getStatus());
        updateProduct.setCategory(product.getCategory());
        productRepository.save(updateProduct);
    }

    @Override
    public void deleteAProduct(int id) {
        Product product = productRepository.getById(id);
        product.setStatus("Inactive");
        productRepository.save(product);
    }

    @Override
    public List<Product> getAllProductsByCategoryId(int id) {
        return productRepository.findProductsByCategory_CategoryId(id);
    }

    @Override
    public ProductDto getProductById(int id) {
        Product product = productRepository.findById(id).stream().findFirst().
                orElseThrow(() -> new NotFoundException("Product with id " + id +" not found!"));
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        return productDto;
    }


}


