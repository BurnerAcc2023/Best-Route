package com.lucidity.BestRoute.entity;

import lombok.Data;

@Data
public class Address {
    private Long addressId;
    private String addressName;
    private String addressLine1;
    private String addressLine2;
    private Double latitude;
    private Double longitude;
}
