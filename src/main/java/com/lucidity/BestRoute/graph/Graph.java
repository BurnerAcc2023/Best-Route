package com.lucidity.BestRoute.graph;

import com.lucidity.BestRoute.entity.Node;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Graph {

    public Map<Node, PriorityQueue<Map.Entry<Node,Integer>>> adjMap;

    public Graph() {
        adjMap = new HashMap<>();
    }

    public void addNode(Node node) {
        adjMap.putIfAbsent(node, new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue)));
    }

    public void addEdge(Node node1, Node node2, Integer weight) {
        adjMap.get(node1).add(Map.entry(node2, weight));
    }
}
