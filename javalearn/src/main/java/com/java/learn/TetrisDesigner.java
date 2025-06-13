package com.java.learn;

import java.util.*;

public class TetrisDesigner {

    static int[][][] SHAPES = new int[5][][];

    static {
        // A
        SHAPES[0] = new int[][]{
            {1}
        };
        // B
        SHAPES[1] = new int[][]{
            {1, 1, 1, 1}
        };
        // C
        SHAPES[2] = new int[][]{
            {1, 1},
            {1, 1}
        };
        // D
        SHAPES[3] = new int[][]{
            {1, 0},
            {1, 1},
            {1, 0}
        };
        // E
        SHAPES[4] = new int[][]{
            {0, 1, 0},
            {1, 1, 1}
        };
    }

    public static int[][] solution(int n, int m, char[] figures) {
        int[][] grid = new int[n][m];

        for (int index = 0; index < figures.length; index++) {
            int figIndex = figures[index] - 'A';
            int[][] shape = SHAPES[figIndex];
            int shapeRows = shape.length;
            int shapeCols = shape[0].length;

            boolean placed = false;

            for (int i = 0; i <= n - shapeRows && !placed; i++) {
                for (int j = 0; j <= m - shapeCols && !placed; j++) {
                    if (canPlace(grid, shape, i, j)) {
                        place(grid, shape, i, j, index + 1);
                        placed = true;
                    }
                }
            }
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

    private static void place(int[][] grid, int[][] shape, int row, int col, int value) {
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                if (shape[i][j] == 1) {
                    grid[row + i][col + j] = value;
                }
            }
        }
    }

    // Test the implementation
    public static void main(String[] args) {
        int n = 4, m = 4;
        char[] figures = {'D', 'B', 'A', 'C'};
        int[][] result = solution(n, m, figures);

        for (int[] row : result) {
            System.out.println(Arrays.toString(row));
        }
    }
}