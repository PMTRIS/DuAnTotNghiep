package com.mt.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.mt.entity.Order;
import com.mt.entity.OrderDetail;
import com.mt.entity.Payment;

public interface OrderService {
    Order create(JsonNode orderData);
    List<Order> findByUsername(String username);
    void createOrder(Order order, List<OrderDetail> orderDetails);
    List<Order> findAll();
	Order save(Order order);
}
