package com.elevate.app.takeaway.model;

import javax.validation.constraints.NotNull;
import java.util.List;

public class OrderRequest {
    @NotNull(message = "User is required")
    private long userId;

    @NotNull(message = "Address is required")
    private long addressId;
    private List<ProductModel> products;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductModel> products) {
        this.products = products;
    }

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }
}
