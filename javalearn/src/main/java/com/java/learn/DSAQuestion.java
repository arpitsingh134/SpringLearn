package com.java.learn;

import java.util.Arrays;

public class DSAQuestion {


    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Enter size of array:");
//        int size = scanner.nextInt();
//        int[] arr = new int[size];
//
//        System.out.println("Enter array elements separated by space:");
//        for (int i = 0; i < size; i++) {
//            arr[i] = scanner.nextInt();
//        }
//
//        System.out.println("Input Array: " + Arrays.toString(arr));
//
//        System.out.println(buySellProfit2(arr));


        //binary search
        int arr[] = {1, 2, 3, 7, 8, 9, 9, 9, 11};
        System.out.println(binarySearchLowerBound(arr, 0, arr.length - 1, 20, arr.length));

//        int[] result = buySellProfit2(arr);
//        System.out.println("Maximum subarray: " + Arrays.toString(result));
    }


    //o(n2)
    public static int maxSubarraySumBrute(int arr[]) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                max = Math.max(max, sum);
            }
        }
        return max;
    }


    public static int[] maxSubarray1(int[] arr) {
        int max = arr[0], sum = arr[0];
        int start = 0, end = 0, tempStart = 0;

        for (int i = 1; i < arr.length; i++) {
            if (sum < 0) {
                sum = arr[i];
                tempStart = i;
            } else {
                sum += arr[i];
            }

            if (sum > max) {
                max = sum;
                start = tempStart;
                end = i;
            }
        }

        return Arrays.copyOfRange(arr, start, end + 1);
    }


    public static int maxSubArraySum(int[] arr) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        int mof = Integer.MIN_VALUE;
        for (int j : arr) {
            sum += j;
            max = Math.max(max, sum);
            sum = Math.max(sum, 0);
        }
        return max;
    }


    public static int[] maxSubarray(int[] arr) {
        int start = -1, ansEnd = -1, ansStart = -1, sum = 0, max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {

            if (sum == 0) {
                start = i;
            }
            sum += arr[i];
            if (sum > max) {
                max = sum;
                ansStart = start;
                ansEnd = i;
            }
            sum = Math.max(sum, 0);
        }
        return new int[]{ansStart, ansEnd, max};
    }

    //part 1
    public static int buyStockProfit1(int[] arr) {
        int profit = 0, cost = 0;
        int min = arr[0];
        for (int i = 1; i < arr.length - 1; i++) {
            cost = arr[i] - min;
            profit = Math.max(profit, cost);
            min = Math.min(min, arr[i]);
        }
        return profit;
    }

    //part 2

    public static int buySellProfit2(int[] arr) {
        int profit = 0, cost = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            //buy day with profit
            if (arr[i] < arr[i + 1]) {
                cost = arr[i + 1] - arr[i];
                profit += cost;
            }

        }
        return profit;
    }

    private static int binarySearchRS(int[] arr, int start, int end, int target) {
        //base condition
        if (start > end) {
            return -1;
        }

        int mid = start + (end - start) / 2;
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] > target) {
            return binarySearchRS(arr, start, mid - 1, target);
        } else {
            return binarySearchRS(arr, mid + 1, end, target);
        }
    }



    private static int binarySearch(int[] arr, int start, int end, int target) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }


    //lower bound arr[index]>=target
    private static int binarySearchLowerBound(int[] arr, int start, int end, int target, int ans) {

        int mid = start + (end - start) / 2;
        if (start > end) {
            return ans;
        }
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] > target) {
            return binarySearchLowerBound(arr, start, mid - 1, target, mid);
        } else {
            return binarySearchLowerBound(arr, mid + 1, end, target, mid);
        }
    }

}
