package com.lucidity.BestRoute.entity;

import lombok.Data;

import java.util.List;

@Data
public class DeliveryExecutive {
    private String name;
    private Long deliveryExecutiveId;
    private Double currentLatitude;
    private Double currentLongitude;
    private List<OrderHistory> pastOrders;
}
