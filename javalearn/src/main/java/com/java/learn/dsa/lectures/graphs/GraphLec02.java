package com.java.learn.dsa.lectures.graphs;

import com.java.learn.dsa.lectures.graphs.GraphUtils.Edge;

import java.util.*;


public class GraphLec02 {

    public static void main(String[] args) {
        ArrayList<Edge>[] graph = GraphUtils.initialzeGraph();
        ArrayList<Edge>[] graphWithNegative = GraphUtils.createGraphNegative(7);
        dijkstra(graph, 0);
        bellmanFord(graph, 0);
        bellmanFord(graphWithNegative, 0);
        ArrayList<Edge>[] directGraphForSCC = GraphUtils.createDirectGraphForSCC();
        kosarajuAlgorithm(directGraphForSCC, 5);


        ArrayList<Edge>[] graphForBridge = GraphUtils.createGraphForBridge();

        System.out.println("Bridge graph:");

        getBridge(graphForBridge, 6);

        getAP(graphForBridge, 6);

    }

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


    //Dijkstra Algorithm
    private static class Pair implements Comparable<Pair> {
        int node;
        int weight;

        public Pair(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Pair o) {
            return this.weight - o.weight;
        }
    }


    public static void dijkstra(ArrayList<Edge>[] graph, int src) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int[] dist = new int[graph.length];
        boolean[] visited = new boolean[graph.length];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        pq.add(new Pair(src, 0));
        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            if (visited[curr.node]) continue;
            visited[curr.node] = true;
            for (Edge edge : graph[curr.node]) {
                int v = edge.dist;
                int u = edge.src;
                int wt = edge.weight;
                if (dist[v] > dist[u] + wt) {
                    dist[v] = dist[u] + wt;
                    pq.add(new Pair(v, dist[v]));
                }
            }

        }
        System.out.println(Arrays.toString(dist));
    }


    // Prims Algorithm using PriorityQueue to find Minimum Spanning Tree (MST)
    public int primsAlgorithm(ArrayList<Edge>[] graph, int src) {
        int totalCost = 0;
        boolean[] visited = new boolean[graph.length];
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        pq.add(new Pair(src, 0)); // Starting node with 0 cost

        while (!pq.isEmpty()) {
            Pair current = pq.poll();

            if (visited[current.node]) continue;

            visited[current.node] = true;
            totalCost += current.weight;

            for (Edge edge : graph[current.node]) {
                if (!visited[edge.dist]) {
                    pq.add(new Pair(edge.dist, edge.weight));
                }
            }
        }

        return totalCost;
    }

    //bellman Ford
    public static void bellmanFord(ArrayList<Edge>[] graph, int src) {
        int V = graph.length;
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        for (int k = 0; k < V - 1; k++) {
            for (int u = 0; u < V; u++) {
                for (Edge edge : graph[u]) {
                    int v = edge.dist;
                    int wt = edge.weight;
                    if (dist[u] != Integer.MAX_VALUE && dist[v] > dist[u] + wt) {
                        dist[v] = dist[u] + wt;
                    }
                }
            }
        }

        for (int u = 0; u < V; u++) {
            for (Edge edge : graph[u]) {
                int v = edge.dist;
                int wt = edge.weight;
                if (dist[u] != Integer.MAX_VALUE && dist[v] > dist[u] + wt) {
                    System.out.println("Negative weight cycle detected");
                    return;
                }
            }
        }

        System.out.println(Arrays.toString(dist));
    }

    public static int countSubArrayHasProductEqualToLessThenK(int arr[], int k) {
        int product = 1;
        int count = 0, start = 0, end = 0;
        while (start < arr.length && end < arr.length) {
            product *= arr[end];
            while (product >= k) {
                product /= arr[start];
                start++;
            }
            count = end - start + 1;
            end++;
        }
        return count;
    }


    // Main function to find Strongly Connected Components using Kosaraju's Algorithm
    public static void kosarajuAlgorithm(ArrayList<Edge>[] graph, int V) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];

        // Step 1: Topological sort using DFS (on original graph)
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                topoSort(graph, i, visited, stack);
            }
        }

        // Step 2: Transpose the graph
        ArrayList<Edge>[] transpose = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            transpose[i] = new ArrayList<>();
        }

        for (int i = 0; i < V; i++) {
            for (Edge edge : graph[i]) {
                transpose[edge.dist].add(new Edge(edge.dist, edge.src, edge.weight));  // Reverse the edge
            }
        }

        // Step 3: DFS on transposed graph in the order of the stack
        Arrays.fill(visited, false);
        List<List<Integer>> components = new ArrayList<>();

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                List<Integer> component = new ArrayList<>();
                dfs(transpose, node, visited, component);
                components.add(new ArrayList<>(component));
            }
        }

        System.out.println("All SCCs: " + components);
    }

    // Topological sort helper (DFS-based)
    private static void topoSort(ArrayList<Edge>[] graph, int src, boolean[] visited, Stack<Integer> stack) {
        visited[src] = true;
        for (Edge edge : graph[src]) {
            if (!visited[edge.dist]) {
                topoSort(graph, edge.dist, visited, stack);
            }
        }
        stack.push(src);
    }

    // Standard DFS for collecting SCC from transposed graph
    private static void dfs(ArrayList<Edge>[] graph, int src, boolean[] visited, List<Integer> component) {
        visited[src] = true;
        component.add(src);
        for (Edge edge : graph[src]) {
            if (!visited[edge.dist]) {
                dfs(graph, edge.dist, visited, component);
            }
        }
    }


    //Find Bridge in graph
    public static void getBridge(ArrayList<Edge>[] graph, int v) {
        int[] dt = new int[v];
        int[] low = new int[v];
        int time = 0;
        boolean[] visited = new boolean[v];
        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                dfsForBridge(graph, i, visited, dt, low, time, -1);
            }
        }
    }

    private static void dfsForBridge(ArrayList<Edge>[] graph, int curr, boolean[] visited, int[] dt, int[] low, int time, int parent) {
        visited[curr] = true;
        dt[curr] = low[curr] = ++time;
        for (Edge edge : graph[curr]) {
            if (edge.dist == parent) {
                continue;
            }
            if (!visited[edge.dist]) {
                dfsForBridge(graph, edge.dist, visited, dt, low, time, curr);
                low[curr] = Math.min(low[curr], low[edge.dist]);
                if (dt[curr] < low[edge.dist]) {
                    System.out.println("Bridge found from : " + curr + "  -->     " + edge.dist);
                }
            } else {
                low[curr] = Math.min(low[curr], dt[edge.dist]);
            }
        }
    }

    //Find Articulation in graph
    public static void getAP(ArrayList<Edge>[] graph, int v) {
        int[] dt = new int[v];
        int[] low = new int[v];
        int time = 0;
        boolean[] visited = new boolean[v];
        boolean[] articulationPoint = new boolean[v];
        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                dfsForAP(graph, i, visited, dt, low, articulationPoint, time, -1);
            }
        }

        for (int i = 0; i < v; i++) {
            if (articulationPoint[i]) {
                System.out.println("articulationPoint[" + i + "]b  ");
            }
        }
    }


    //articulation point
    private static void dfsForAP(ArrayList<Edge>[] graph, int curr, boolean[] visited, int[] dt, int[] low, boolean[] ap, int time, int parent) {
        visited[curr] = true;
        int child = 0;
        dt[curr] = low[curr] = ++time;
        for (Edge edge : graph[curr]) {
            if (edge.dist == parent) {
                continue;
            }
            if (!visited[edge.dist]) {
                dfsForAP(graph, edge.dist, visited, dt, low, ap, time, curr);
                low[curr] = Math.min(low[curr], low[edge.dist]);

                //parent!=-1
                if (dt[curr] <= low[edge.dist] && parent != -1) {
                    ap[curr] = true;
                }
                child++;

            } else {
                low[curr] = Math.min(low[curr], dt[edge.dist]);
            }
        }
        if (parent == -1 && child > 1) {
            ap[curr] = true;
        }
    }
}
