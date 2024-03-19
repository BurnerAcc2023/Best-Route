package com.lucidity.BestRoute.service.impl;

import com.lucidity.BestRoute.dto.request.OrderRequest;
import com.lucidity.BestRoute.entity.*;
import com.lucidity.BestRoute.entity.enums.NodeType;
import com.lucidity.BestRoute.graph.Graph;
import com.lucidity.BestRoute.mapper.OrderMapper;
import com.lucidity.BestRoute.service.CustomerService;
import com.lucidity.BestRoute.service.DeliveryExecutiveService;
import com.lucidity.BestRoute.service.OrderService;
import com.lucidity.BestRoute.service.RestaurantService;
import com.lucidity.BestRoute.util.HaversineDistanceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CustomerService customerService;
    private final RestaurantService restaurantService;
    private final DeliveryExecutiveService deliveryExecutiveService;
    private final OrderMapper orderMapper;

    @Override
    public String bestRoute(List<OrderRequest> orderRequests) {
        List<Customer> customers = new ArrayList<>();
        List<Restaurant> restaurants = new ArrayList<>();
        DeliveryExecutive deliveryExecutive = deliveryExecutiveService.getDeliveryExecutiveById(orderRequests.getFirst().getDeliveryExecutiveId());
        Map<Restaurant, Order> restaurantOrderMap = new HashMap<>();

        for (OrderRequest request : orderRequests) {
            Customer customer = customerService.getCustomerById(request.getCustomerId());
            Restaurant restaurant = restaurantService.getRestaurantById(request.getRestaurantId());
            Order order = orderMapper.convert(request);
            restaurant.setCurrentOrderPreparationTime(order.getPreparationTime());
            restaurants.add(restaurant);
            customers.add(customer);
            restaurantOrderMap.put(restaurant, order);
        }

        List<Node> nodeList = new ArrayList<>(restaurants);
        nodeList.addAll(customers);
        Node start = getRestaurantWithMinDistance(deliveryExecutive, restaurants);
        Graph graph = initialiseGraph(nodeList);
        List<String> bestRoute = getBestRoute(graph, restaurantOrderMap, nodeList.size(), start);

        return String.join("->", bestRoute);
    }

    private Graph initialiseGraph(List<Node> nodes) {
        Graph graph = new Graph();
        for (Node node1 : nodes) {
            graph.addNode(node1);
            for (Node node2 : nodes) {
                if (Objects.equals(node1, node2)) continue;
                int time = HaversineDistanceUtils.getTime(node1, node2);
                graph.addEdge(node1, node2, time);
            }
        }
        return graph;
    }

    private List<String> getBestRoute(Graph graph, Map<Restaurant, Order> restaurantOrderMap, int size, Node start) {
        List<String> bestRoute = new ArrayList<>();

        Set<Node> visited = new HashSet<>();
        while (visited.size() != size) {
            visited.add(start);
            bestRoute.add(start.getName());
            if (visited.size() == size) break;

            boolean nextNode = false;

            while (start.getType() == NodeType.CUSTOMER) {
                var pq = graph.adjMap.get(start);
                List<Map.Entry<Node, Integer>> polledElements = new ArrayList<>();
                var entry = pq.peek();
                if (entry.getKey().getType() == NodeType.RESTAURANT && !visited.contains(entry.getKey())) {
                    start = pq.poll().getKey();
                    nextNode = true;

                    pq.addAll(polledElements);
                } else {
                    var polledEntry = pq.poll();
                    if (visited.contains(polledEntry.getKey())) continue;
                    polledElements.add(polledEntry);
                }
            }

            if (start.getType() == NodeType.RESTAURANT && !nextNode) {
                start = restaurantOrderMap.get(start).getCustomer();
            }
        }

        return bestRoute;
    }

    private Restaurant getRestaurantWithMinDistance(DeliveryExecutive deliveryExecutive, List<Restaurant> restaurants) {
        int min = Integer.MAX_VALUE;
        Restaurant nearestRestaurant = null;
        for (Restaurant restaurant : restaurants) {
            int time = HaversineDistanceUtils.getTime(deliveryExecutive, restaurant) +
                    getPreparationTime(restaurant);
            if (time < min) {
                min = time;
                nearestRestaurant = restaurant;
            }
        }
        return nearestRestaurant;
    }

    private int getPreparationTime(Restaurant restaurant) {
        return restaurant.getCurrentOrderPreparationTime();
    }
}
