package com.java.learn.dsa.grind75;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Week2 {

    public static void main(String[] args) {
        Week2 week = new Week2();

//        System.out.println(week.missingNumber(new int[]{0, 1}));

//        System.out.println(week.reverse(1234));


        int[] newArr = {4, 8};
        int[][] arr = {{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};
//        int[][] insert = week.insert(arr, newArr);
        System.out.println(Arrays.deepToString(week.updateMatrix(arr)));

    }

    public int missingNumber(int[] nums) {

        int i = 0;
        while (i < nums.length) {
            int validIndex = nums[i];
            if (validIndex != i) {
                if (validIndex < nums.length) {
                    int temp = nums[i];
                    nums[i] = nums[validIndex];
                    nums[validIndex] = temp;
                } else {
                    i++;
                }
            } else {
                i++;
            }
        }

        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != j) {
                return j;
            }
        }
        return nums.length;
    }

    public int missingNumberTwo(int[] nums) {
        int xor = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            xor ^= i ^ nums[i];
        }

        return xor ^ n;
    }


    private void swap(int i, int j) {
        i = i ^ j;
        j = i ^ j;
        i = i ^ j;
    }


    public boolean isPalindrome(int x) {
        return x == reverse(x);
    }

    private int reverse(int x) {

        int reversed = 0;

        while (x != 0) {
            int digit = x % 10;
            if (reversed > (Integer.MAX_VALUE - digit) / 10) return -1;
            reversed = reversed * 10 + digit;
            x /= 10;
        }

        return reversed;
    }


    public TreeNode sortedArrayToBST(int[] nums) {

        if (nums == null || nums.length == 0) return null;
        return createBST(nums, 0, nums.length - 1);

    }


    private TreeNode createBST(int[] nums, int start, int end) {

        if (start > end) return null;
        int mid = start - (start + end) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = createBST(nums, start, mid - 1);
        root.right = createBST(nums, mid + 1, end);

        return root;

    }

    public int reverseBits(int n) {

        int result = 0;
        for (int i = 0; i < 32; i++) {
            result <<= 1; // Shift result left to make space
            result |= (n & 1); // Add the last bit of n to result
            n >>= 1; // Shift n right to get next bit
        }
        return result;
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {

        if (subRoot == null) return true;
        if (root == null) return false;
        if (compareTree(root, subRoot)) {
            return true;
        }
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private boolean compareTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null || p.val != q.val) {
            return false;
        }
        return compareTree(p.left, q.left) && compareTree(p.right, q.right);
    }

    //new version

    public boolean isSubtreeTwo(TreeNode root, TreeNode subRoot) {
        String rootSerialized = serialize(root);
        String subRootSerialized = serialize(subRoot);

        // Check if the serialized subtree string is a substring of the serialized root string
        return rootSerialized.contains(subRootSerialized);
    }

    private String serialize(TreeNode node) {
        if (node == null) return "#";  // Use '#' to represent null nodes
        return node.val + "," + serialize(node.left) + "," + serialize(node.right);
    }

    public int[] sortedSquares(int[] nums) {
        if (nums == null || nums.length == 0)
            return null;

        int[] result = new int[nums.length];

        int i = 0, j = nums.length - 1;
        //initialize i for -ive
        while (i < nums.length && nums[i] < 0) {
            i++;
        }
        i--;
        //initialize i for +iveive for 0
        while (j >= 0 && nums[j] >= 0) {
            --j;
        }
        j++;
        int k = 0;


        while (i >= 0 && j < nums.length) {
            if ((nums[i] * nums[i]) >= (nums[j] * nums[j])) {
                result[k] = nums[j] * nums[j];
                j++;
            } else {
                result[k] = nums[i] * nums[i];
                i--;
            }
            k++;

        }
        //check for i>=0


        while (i >= 0) {
            result[k++] = nums[i] * nums[i];
            --i;
        }
        while (j < nums.length) {
            result[k++] = nums[j] * nums[j];
            j++;
        }

        return result;
    }

    public int[] sortedSquaresTwo(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        int left = 0, right = n - 1;
        int pos = n - 1;  // Start filling from the end

        while (left <= right) {
            int leftSq = nums[left] * nums[left];
            int rightSq = nums[right] * nums[right];

            if (leftSq > rightSq) {
                result[pos--] = leftSq;
                left++;
            } else {
                result[pos--] = rightSq;
                right--;
            }
        }

        return result;
    }


    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int currSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currSum = Math.max(nums[i], currSum + nums[i]);
            maxSum = Math.max(maxSum, currSum);
        }

        return maxSum;
    }

    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i != k) {
                    nums[k] = nums[i];
                    nums[i] = 0;
                }
                k++;
            }
        }
    }


    private void printstack(Stack<int[]> stack) {
        System.out.print("stack:--> ");
        for (int[] interval : stack) {

            System.out.print(Arrays.toString(interval) + ",");
        }
        System.out.println();
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int[][] result = new int[intervals.length + 1][2];
        int i = 0, j = 0;
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            result[j++] = intervals[i++];
        }
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval = getIntersection(intervals[i++], newInterval);
        }
        result[j++] = newInterval;

        while (i < intervals.length) {
            result[j++] = intervals[i++];
        }
        return Arrays.copyOf(result, j);
    }

    private int[] getIntersection(int[] a, int[] b) {
        return new int[]{Math.min(a[0], b[0]), Math.max(a[1], b[1])};
    }

    private boolean isOverlapped(int[] a, int[] b) {
        return Math.max(a[0], b[0]) < Math.min(a[1], b[1]);
    }

    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        Queue<int[]> queue = new LinkedList<>();
        int[][] result = new int[m][n];

        // Step 1: Initialize result and queue
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    result[i][j] = 0;
                    queue.offer(new int[]{i, j});
                } else {
                    result[i][j] = -1; // Mark unvisited
                }
            }
        }



        System.out.println("result ::" + Arrays.deepToString(result));

        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        // Step 2: BFS
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int row = cell[0], col = cell[1];

            for (int[] dir : directions) {
                int r = row + dir[0];
                int c = col + dir[1];

                if (r >= 0 && r < m && c >= 0 && c < n && result[r][c] == -1) {
                    result[r][c] = result[row][col] + 1;
                    queue.offer(new int[]{r, c});
//                    System.out.println("queue:" + queue);
                    System.out.println("result ::" + Arrays.deepToString(result));
                }
            }
        }

        return result;
    }


}
