package com.elevate.app.takeaway.service;

import com.elevate.app.takeaway.dto.product.Product;
import com.elevate.app.takeaway.model.ProductModel;

import java.util.List;

public interface IProductService {
    void createProduct(ProductModel productModel);
    List<Product> getAllProducts();
    void updateProduct(Product product, long productId);
}
