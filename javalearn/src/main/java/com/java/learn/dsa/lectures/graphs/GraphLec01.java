package com.java.learn.dsa.lectures.graphs;

import com.java.learn.dsa.lectures.graphs.GraphUtils.Edge;

import java.util.*;


/*
Graph Traversal & Cycle Detection - Quick Revision

1. **DFS (Depth-First Search)**
   - Uses recursion.
   - Explore as far as possible along each branch.
   - For printing all paths from source to destination, apply backtracking:
     - Mark visited before the recursive call.
     - Unmark (visited = false) after the recursive call.

2. **BFS (Breadth-First Search)**
   - Uses a queue.
   - Good for finding shortest path in unweighted graphs.
   - Level-wise traversal.

3. **Cycle Detection in Undirected Graph**
   - Use a `visited[]` array and track the parent node.
   - If a visited neighbor is not the parent → **Cycle exists**.

4. **Cycle Detection in Directed Graph**
   - Use a `visited[]` array and a `recStack[]` (recursion stack).
   - If a node is already in the recursion stack → **Cycle exists**.

5. **Topological Sort (Directed Acyclic Graph - DAG)**
   - Use DFS and a stack.
   - On backtracking (after visiting all neighbors), push the current node to the stack.
   - Final topological order is the reverse of the stack.

*/

public class GraphLec01 {

    public static void main(String[] args) {
        ArrayList<Edge>[] graph = GraphUtils.initialzeGraph();

        System.out.println("bfs");
        bfs(graph);

        System.out.println("dfs");
        dfs(graph);

        System.out.println("print all paths -- using dfs");
        printPaths(graph, 0, 6);

        System.out.println(isCyclicPresent(graph));


        System.out.println("directed graph testing");
        ArrayList<Edge>[] graphWithCycle = GraphUtils.createDirectedGraphWithCycle(7);
        ArrayList<Edge>[] graphWithOutCycle = GraphUtils.createDirectedGraphWithoutCycle(7);
        ArrayList<Edge>[] DAG = GraphUtils.createDAGGraph(7);


        System.out.println("Cycle Detected -> "+isCyclicPresentDirected(graphWithCycle));


        System.out.println("Cycle Detected -> "+isCyclicPresentDirected(graphWithOutCycle));


        System.out.println("printing withcycle graph in topological order"+topologicalSort(graphWithOutCycle));


        System.out.println("printing DAG graph in DAF order"+topologicalSort(DAG));

    }


    //O(v+E)
    public static void bfs(ArrayList<Edge>[] graph) {
        int vertex = graph.length;
        boolean[] vis = new boolean[vertex];

        // To check all disconnected component
        for (int i = 0; i < vertex; i++) {
            if (!vis[i]) bsfHelper(graph, vertex, vis, i);
        }
    }

    private static void bsfHelper(ArrayList<Edge>[] graph, int v, boolean[] vis, int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if (!vis[curr]) {
                vis[curr] = true;
                System.out.print(curr + " ");
                for (Edge edges : graph[curr]) {
                    queue.offer(edges.dist);
                }
            }
        }

        System.out.println();
    }


    public static void dfs(ArrayList<Edge>[] graph) {

        int vertex = graph.length;
        boolean[] vis = new boolean[vertex];

        // To check all disconnected component
        for (int i = 0; i < vertex; i++) {
            if (!vis[i]) dfsHelper(graph, i, vis);
        }
        System.out.println();
    }

    private static void dfsHelper(ArrayList<Edge>[] graph, int curr, boolean[] vis) {
        System.out.print(curr + " ");
        vis[curr] = true;
        for (Edge edge : graph[curr]) {
            if (!vis[edge.dist]) dfsHelper(graph, edge.dist, vis);
        }
    }


    // Print all paths from source to target
    public static void printPaths(ArrayList<Edge>[] graph, int start, int target) {
        boolean[] visited = new boolean[graph.length];
        printPathHelper(graph, start, target, visited, start + "");
    }

    private static void printPathHelper(ArrayList<Edge>[] graph, int curr, int target, boolean[] visited, String pathSoFar) {
        if (curr == target) {
            System.out.println(pathSoFar);
            return;
        }


        for (Edge edge : graph[curr]) {
            if (!visited[edge.dist]) {
                visited[curr] = true;
                printPathHelper(graph, edge.dist, target, visited, pathSoFar + " -> " + edge.dist);
                visited[curr] = false;
            }
        }

    }


    //for undirected graph
    public static boolean isCyclicPresent(ArrayList<Edge>[] graph) {
        int vertex = graph.length;
        boolean[] vis = new boolean[vertex];

        // To check all disconnected components
        for (int i = 0; i < vertex; i++) {
            if (!vis[i]) {
                if (isCyclicPresentHelper(graph, i, -1, vis)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isCyclicPresentHelper(ArrayList<Edge>[] graph, int curr, int parent, boolean[] vis) {
        vis[curr] = true;

        for (Edge edge : graph[curr]) {
            int neighbor = edge.dist;

            if (!vis[neighbor]) {
                if (isCyclicPresentHelper(graph, neighbor, curr, vis)) {
                    return true;
                }
            }
            // if visited and not parent => cycle
            else if (neighbor != parent) {
                return true;
            }
        }
        return false;
    }


    //for undirected graph
    public static boolean isCyclicPresentDirected(ArrayList<Edge>[] graph) {
        int vertex = graph.length;
        boolean[] vis = new boolean[vertex];

        boolean[] recStack = new boolean[vertex];
        // To check all disconnected components
        for (int i = 0; i < vertex; i++) {
            if (!vis[i]) {
                if (isCyclicPresentDirectedHelper(graph, i, vis, recStack)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isCyclicPresentDirectedHelper(ArrayList<Edge>[] graph, int curr, boolean[] vis, boolean[] recStack) {
        vis[curr] = true;
        recStack[curr] = true;

        for (Edge edge : graph[curr]) {
            int neighbor = edge.dist;

            if (recStack[neighbor]) {
                return true;
            }

            if (!vis[neighbor]) {
                if (isCyclicPresentDirectedHelper(graph, neighbor, vis, recStack)) {
                    return true;
                }
            }

        }
        recStack[curr] = false;
        return false;
    }


    public static List<Integer> topologicalSort(ArrayList<Edge>[] graph) {
        int vertex = graph.length;
        boolean[] vis = new boolean[vertex];
        Stack<Integer> stack = new Stack<>();

        // Visit all components
        for (int i = 0; i < vertex; i++) {
            if (!vis[i]) {
                topologicalSortHelper(graph, i, vis, stack);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());  // Topological order from stack
        }
        return result;
    }

    private static void topologicalSortHelper(ArrayList<Edge>[] graph, int curr, boolean[] vis, Stack<Integer> stack) {
        vis[curr] = true;
        for (Edge edge : graph[curr]) {
            if (!vis[edge.dist]) { // check the neighbor, not current
                topologicalSortHelper(graph, edge.dist, vis, stack);
            }
        }
        stack.push(curr);  // After visiting all neighbors, add to stack
    }


}
