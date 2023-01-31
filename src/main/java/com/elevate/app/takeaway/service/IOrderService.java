package com.elevate.app.takeaway.service;

import com.elevate.app.takeaway.dto.order.Order;
import com.elevate.app.takeaway.dto.order.OrderResponse;
import com.elevate.app.takeaway.model.OrderRequest;

import java.util.List;

public interface IOrderService {
    void createOrder(OrderRequest orderRequest);
    List<Order> getAllOrders();
    List<OrderResponse> getOrderByUserId(long userId);

    long getOrderByCity(String city);
}
