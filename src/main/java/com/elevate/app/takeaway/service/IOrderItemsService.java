package com.elevate.app.takeaway.service;

import com.elevate.app.takeaway.dto.order.OrderItem;

import java.util.List;

public interface IOrderItemsService {
    void createOrderItem(OrderItem orderItem);
    List<OrderItem> getAllOrderItems();
}
