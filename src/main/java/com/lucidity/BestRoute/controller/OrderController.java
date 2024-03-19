package com.lucidity.BestRoute.controller;

import com.lucidity.BestRoute.dto.request.OrderRequest;
import com.lucidity.BestRoute.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/best-route")
    private String getBestRoute(@RequestBody List<OrderRequest> orderRequests) {
        return orderService.bestRoute(orderRequests);
    }
}
