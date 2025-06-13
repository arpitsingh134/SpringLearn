package com.java.learn;

import java.util.*;

public class GridDesigner {

    static Map<Character, int[][]> shapeMap = new HashMap<>();

    static {
        shapeMap.put('A', new int[][]{{1, 1}, {1, 1}});
        shapeMap.put('B', new int[][]{{1, 1, 1}});
        shapeMap.put('C', new int[][]{{1}, {1}, {1}});
        shapeMap.put('D', new int[][]{{1, 1, 0}, {0, 1, 1}});
    }

    public static int[][] solution(int n, int m, char[] figures) {
        int[][] grid = new int[n][m];
        int id = 1;

        for (char fig : figures) {
            int[][] shape = shapeMap.get(fig);
            boolean placed = false;

            for (int i = 0; i <= n - shape.length && !placed; i++) {
                for (int j = 0; j <= m - shape[0].length && !placed; j++) {
                    if (canPlace(grid, shape, i, j)) {
                        placeShape(grid, shape, i, j, id);
                        placed = true;
                    }
                }
            }

            id++;
        }

        return grid;
    }

    private static boolean canPlace(int[][] grid, int[][] shape, int row, int col) {
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                if (shape[i][j] == 1 && grid[row + i][col + j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void placeShape(int[][] grid, int[][] shape, int row, int col, int id) {
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                if (shape[i][j] == 1) {
                    grid[row + i][col + j] = id;
                }
            }
        }
    }

    // For debugging
    public static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static void main(String[] args) {
        int n = 4, m = 4;
        char[] figures = {'D', 'B', 'A', 'C'};
        int[][] result = solution(n, m, figures);
        printGrid(result);
    }
}