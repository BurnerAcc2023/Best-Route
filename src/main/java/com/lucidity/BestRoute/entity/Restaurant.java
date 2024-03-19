package com.lucidity.BestRoute.entity;

import com.lucidity.BestRoute.entity.enums.NodeType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Restaurant extends Node {

    public Restaurant() {
        type = NodeType.RESTAURANT;
    }
    private Long restaurantId;
    private Integer currentOrderPreparationTime;
    private List<OrderHistory> pastOrders;
}
