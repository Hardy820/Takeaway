package com.elevate.app.takeaway.controllers;

import com.elevate.app.takeaway.model.ProductModel;
import com.elevate.app.takeaway.model.ResponseModel;
import com.elevate.app.takeaway.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    
    @Autowired
    ProductService productService;
    
    @GetMapping("/all")
    public ResponseModel getAllProducts() {
        ResponseModel model = new ResponseModel();
        model.data = Collections.singletonList(productService.getAllProducts());
        model.message = "Users Found";
        model.responseCode = HttpStatus.CREATED.value();
        return model;
    }

    @PostMapping("/create")
    public ResponseModel createProduct(@Valid @RequestBody ProductModel productModel) {
        ResponseModel model = new ResponseModel();
        productService.createProduct(productModel);
        model.message = "User Address Created";
        model.responseCode = HttpStatus.CREATED.value();
        return model;
    }

}
