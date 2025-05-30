package com.java.learn.dsa.lectures.graphs;

import java.util.HashMap;

public class GraphLec02 {

    public Node cloneGraph(Node node) {

        return cloneGraphHelper(node, new HashMap<Node, Node>());

    }

    private Node cloneGraphHelper(Node node, HashMap<Node, Node> map) {

        if (node == null) return null;
        if (map.containsKey(node)) return map.get(node);

        Node copy = new Node(node.val);
        map.put(node, copy);
        for (Node neighbor : node.neighbors) {
            copy.neighbors.add(cloneGraphHelper(neighbor, map));
        }

        return copy;
    }


}
