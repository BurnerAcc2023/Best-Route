package com.lucidity.BestRoute.entity;

import com.lucidity.BestRoute.entity.enums.NodeType;
import lombok.Data;

@Data
public class Node {
    private String name;
    protected NodeType type;
    private Address address;
}
