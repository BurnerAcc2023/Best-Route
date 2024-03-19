package com.lucidity.BestRoute.entity;

import com.lucidity.BestRoute.entity.enums.NodeType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Customer extends Node {

    public Customer() {
        type = NodeType.CUSTOMER;
    }
    private Long customerId;
    private List<OrderHistory> pastOrders;
    private String phoneNumber;
}
