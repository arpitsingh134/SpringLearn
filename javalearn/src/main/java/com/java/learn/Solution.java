package com.java.learn;

import java.util.*;

public class Solution {
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int healthyCount = 0;
        
        // Find all initially infected persons and count healthy ones
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    healthyCount++;
                }
            }
        }
        
        // If no healthy persons, return 0
        if (healthyCount == 0) return 0;
        
        // Directions: up, down, left, right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int minutes = 0;
        
        // BFS to simulate infection spread
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean infected = false;
            
            // Process all infections at current minute
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int row = current[0];
                int col = current[1];
                
                // Check all 4 directions
                for (int[] dir : directions) {
                    int newRow = row + dir[0];
                    int newCol = col + dir[1];
                    
                    // Check bounds and if cell has healthy person
                    if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n 
                        && grid[newRow][newCol] == 1) {
                        
                        // Infect the healthy person
                        grid[newRow][newCol] = 2;
                        queue.offer(new int[]{newRow, newCol});
                        healthyCount--;
                        infected = true;
                    }
                }
            }
            
            // If we infected someone this minute, increment time
            if (infected) {
                minutes++;
            }
        }
        
        // If there are still healthy persons, infection couldn't reach them
        return healthyCount == 0 ? minutes : -1;
    }
    
    // Test method with the given example
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1: [[2,1,1],[1,1,0],[0,1,1]]
        int[][] grid1 = {
            {2, 2, 1},
            {1, 1, 0},
            {0, 1, 1}
        };
        System.out.println("Test 1 - Expected: 4, Got: " + solution.orangesRotting(grid1));
        
        // Test case 2: [[2,1,1],[0,1,1],[1,0,1]]
        int[][] grid2 = {
            {2, 1, 1},
            {0, 1, 1},
            {1, 0, 1}
        };
        System.out.println("Test 2 - Expected: -1, Got: " + solution.orangesRotting(grid2));
        
        // Test case 3: [[0,2]]
        int[][] grid3 = {{0, 2}};
        System.out.println("Test 3 - Expected: 0, Got: " + solution.orangesRotting(grid3));
        
        // Show step-by-step simulation for first test case
        System.out.println("\nStep-by-step simulation for [[2,1,1],[1,1,0],[0,1,1]]:");
        simulateInfection();
    }
    
    // Helper method to visualize the infection spread
    public static void simulateInfection() {
        int[][] grid = {
            {2, 1, 1},
            {1, 1, 0},
            {0, 1, 1}
        };
        
        System.out.println("Minute 0 (Initial):");
        printGrid(grid);
        
        // Simulate each minute manually for demonstration
        int minute = 1;
        
        // Minute 1: (0,0) infects (0,1) and (1,0)
        grid[0][1] = 2;
        grid[1][0] = 2;
        System.out.println("\nMinute " + minute + ":");
        printGrid(grid);
        minute++;
        
        // Minute 2: newly infected cells infect their neighbors
        grid[0][2] = 2;
        grid[1][1] = 2;
        System.out.println("\nMinute " + minute + ":");
        printGrid(grid);
        minute++;
        
        // Minute 3: infection continues
        grid[2][1] = 2;
        System.out.println("\nMinute " + minute + ":");
        printGrid(grid);
        minute++;
        
        // Minute 4: final infection
        grid[2][2] = 2;
        System.out.println("\nMinute " + minute + ":");
        printGrid(grid);
    }
    
    public static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
    }
}