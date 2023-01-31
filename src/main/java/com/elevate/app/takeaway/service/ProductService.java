package com.elevate.app.takeaway.service;

import com.elevate.app.takeaway.dto.product.Product;
import com.elevate.app.takeaway.exceptions.CustomException;
import com.elevate.app.takeaway.model.ProductModel;
import com.elevate.app.takeaway.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{

    @Autowired
    ProductRepository productRepository;

    @Override
    public void createProduct(ProductModel productModel) {
        try {
            Product product = new Product();
            product.setName(productModel.getName());
            product.setCategory(productModel.getCategory());
            product.setPrice(productModel.getPrice());
            product.setType(productModel.getType());
            product.setAvailable(productModel.isAvailable());
            product.setQuantity(productModel.getQuantity());
            productRepository.save(product);
        } catch(Exception e) {
            throw new CustomException("Error storing product record");
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void updateProduct(Product product, long productId) {

    }
}
