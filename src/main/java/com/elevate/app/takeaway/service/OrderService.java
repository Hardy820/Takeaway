package com.elevate.app.takeaway.service;

import com.elevate.app.takeaway.dto.order.Order;
import com.elevate.app.takeaway.dto.order.OrderItem;
import com.elevate.app.takeaway.dto.order.OrderItemResponse;
import com.elevate.app.takeaway.dto.order.OrderResponse;
import com.elevate.app.takeaway.dto.product.Product;
import com.elevate.app.takeaway.dto.user.User;
import com.elevate.app.takeaway.dto.user.UserAddress;
import com.elevate.app.takeaway.exceptions.CustomException;
import com.elevate.app.takeaway.model.OrderRequest;
import com.elevate.app.takeaway.model.ProductModel;
import com.elevate.app.takeaway.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserAddressRepository userAddress;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public void createOrder(OrderRequest orderRequest) {
        User user = userRepository.findById(orderRequest.getUserId()).orElseThrow(
                () -> new CustomException("Invalid user"));

        UserAddress address = userAddress.findById(orderRequest.getAddressId()).orElseThrow(
                () -> new CustomException("Invalid Address"));
        try {
            Order order = new Order();
            order.setUser(user);
            order.setUserAddress(address);
            order.setOrderDate(new Date());
            order.setOrderAmount(orderRequest.getProducts().stream()
                    .map(prod -> prod.getPrice() * prod.getQuantity())
                    .reduce(0.0, Double::sum));
            long orderId = orderRepository.save(order).getOrderId();
            for (ProductModel productModel : orderRequest.getProducts()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrderId(orderId);
                orderItem.setProductId(productModel.getProductId());
                orderItem.setQuantity(productModel.getQuantity());
                orderItemRepository.save(orderItem);
            }
        } catch (Exception e) {
            throw new CustomException("Error processing your order");
        }
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<OrderResponse> getOrderByUserId(long userId) {
        List<OrderResponse> orderResponses = new ArrayList<>();
        Optional<List<Order>> dbOrders = Optional.ofNullable(orderRepository.getOrdersByUserId(userId).orElseThrow(() -> new CustomException("No Orders Found")));
        if(dbOrders.isPresent()) {
            for(Order dbOrder : dbOrders.get()) {
                OrderResponse response = new OrderResponse();
                response.setOrderId(dbOrder.getOrderId());
                response.setOrderDate(dbOrder.getOrderDate());
                response.setOrderAmount(dbOrder.getOrderAmount());
                List<OrderItemResponse> orderItems = new ArrayList<>();
                List<OrderItem> dbOrderItems = orderItemRepository.findByOrderId(dbOrder.getOrderId());
                for(OrderItem orderItem : dbOrderItems) {
                    Product product = productRepository.findById(orderItem.getProductId()).get();
                    OrderItemResponse orderItemResponse = new OrderItemResponse();
                    orderItemResponse.setOrderItemId(orderItem.getOrderItemId());
                    orderItemResponse.setQuantity(orderItem.getQuantity());
                    orderItemResponse.setName(product.getName());
                    orderItemResponse.setCategory(product.getCategory());
                    orderItemResponse.setType(product.getType());
                    orderItemResponse.setPrice(product.getPrice());
                    orderItems.add(orderItemResponse);
                }
                response.setOrderItemResponses(orderItems);
                orderResponses.add(response);
            }
        }
        return orderResponses;
    }

    @Override
    public long getOrderByCity(String city){
       long sumOfCity = orderRepository.getOrderAmountByCity(city);
        return sumOfCity;
    }
}
