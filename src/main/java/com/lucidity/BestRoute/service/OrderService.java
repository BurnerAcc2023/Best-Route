package com.lucidity.BestRoute.service;

import com.lucidity.BestRoute.dto.request.OrderRequest;

import java.util.List;

public interface OrderService {
    String bestRoute(List<OrderRequest> orderRequests);
}
