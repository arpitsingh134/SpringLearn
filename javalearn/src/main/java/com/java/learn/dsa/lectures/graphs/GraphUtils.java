package com.java.learn.dsa.lectures.graphs;

import java.util.ArrayList;

public class GraphUtils {


    public static ArrayList<Edge>[] initialzeGraph() {
        return createGraph(7);
    }

    public static class Edge {
        public int src;
        public int dist;
        public int weight;

        public Edge(int src, int dist, int weight) {
            this.src = src;
            this.dist = dist;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "src=" + src +
                    ", dist=" + dist +
                    ", weight=" + weight +
                    '}';
        }
    }


    /*
    0 → 1 → 3 → 4
    ↓    ↘
    2 → 5 → 6
     */

    public static ArrayList<Edge>[] createDAGGraph(int v) {
        ArrayList<Edge>[] graph = new ArrayList[v];

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        // Directed edges (DAG example)
        graph[0].add(new Edge(0, 1, 1));
        graph[0].add(new Edge(0, 2, 1));
        graph[1].add(new Edge(1, 3, 1));
        graph[2].add(new Edge(2, 3, 1));
        graph[3].add(new Edge(3, 4, 1));
        graph[2].add(new Edge(2, 5, 1));
        graph[5].add(new Edge(5, 6, 1));

        // This graph is acyclic and directed

        return graph;
    }


    public static ArrayList<Edge>[] createDirectedGraphWithCycle(int v) {
        ArrayList<Edge>[] graph = new ArrayList[v];


        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        // Example: Directed graph with a cycle
        // 0 → 1 → 2 → 3 → 1 (cycle)
        graph[0].add(new Edge(0, 1, 1));
        graph[1].add(new Edge(1, 2, 1));
        graph[2].add(new Edge(2, 3, 1));
        graph[3].add(new Edge(3, 1, 1));

        return graph;
        // Back edge creates cycle
    }


    public static ArrayList<Edge>[] createDirectedGraphWithoutCycle(int v) {
        ArrayList<Edge>[] graph = new ArrayList[v];

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        // Example: DAG for topological sort
        // 5 → 0, 5 → 2, 4 → 0, 4 → 1, 2 → 3, 3 → 1
        graph[5].add(new Edge(5, 0, 1));
        graph[5].add(new Edge(5, 2, 1));
        graph[4].add(new Edge(4, 0, 1));
        graph[4].add(new Edge(4, 1, 1));
        graph[2].add(new Edge(2, 3, 1));
        graph[3].add(new Edge(3, 1, 1));

        return graph;
    }


    private static ArrayList<Edge>[] createGraph(int v) {
        ArrayList<Edge>[] graph = new ArrayList[v];

        for (int i = 0; i < v; i++) {
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0, 2, 10));
        graph[0].add(new Edge(0, 1, 20));

        graph[1].add(new Edge(1, 0, 2));
        graph[1].add(new Edge(1, 3, 4));

        graph[2].add(new Edge(2, 0, 20));
        graph[2].add(new Edge(2, 4, 100));

        graph[3].add(new Edge(3, 4, 3));
        graph[3].add(new Edge(3, 5, 50));
        graph[3].add(new Edge(3, 1, 10));

        graph[4].add(new Edge(4, 5, 20));
        graph[4].add(new Edge(4, 3, 100));
        graph[4].add(new Edge(4, 2, 3));

        graph[5].add(new Edge(5, 4, 20));
        graph[5].add(new Edge(5, 3, 100));
        graph[5].add(new Edge(5, 6, 3));

        graph[6].add(new Edge(6, 5, 10));

        return graph;
    }

    private static ArrayList<Edge>[] createGraphWithoutCycle(int v) {
        ArrayList<Edge>[] graph = new ArrayList[v];

        for (int i = 0; i < v; i++) {
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0, 1, 20));
        graph[1].add(new Edge(1, 0, 2));
        graph[1].add(new Edge(1, 3, 4));
        graph[2].add(new Edge(2, 4, 100));
        graph[3].add(new Edge(3, 5, 50));
        graph[3].add(new Edge(3, 1, 10));
        graph[4].add(new Edge(4, 5, 20));
        graph[4].add(new Edge(4, 2, 3));
        graph[5].add(new Edge(5, 4, 20));
        graph[5].add(new Edge(5, 3, 100));
        graph[5].add(new Edge(5, 6, 3));
        graph[6].add(new Edge(6, 5, 10));

        return graph;
    }

}
