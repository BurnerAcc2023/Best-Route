package com.lucidity.BestRoute.entity;

import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class OrderHistory {
    private Long orderHistoryId;
    private Instant orderDeliveredAt;
    private Payment payment;
    private List<OrderItem> orderItems;
    private Customer customer;
    private Restaurant restaurant;
    private DeliveryExecutive deliveryExecutive;
    private Integer timeTook;

}
