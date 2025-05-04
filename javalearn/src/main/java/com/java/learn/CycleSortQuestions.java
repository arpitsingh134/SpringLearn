package com.java.learn;

import java.util.ArrayList;
import java.util.List;

public class CycleSortQuestions {
    public static void main(String[] args) {
        int[] arr = {1, -3, 2, 4, 5, -2};
        System.out.println(maxSubArraySumWithOneDeletion(arr));
    }

    //only work on continuous array :unsorted array
    public static void cyclicSort(int[] arr) {
        int i = 0;
        while (i < arr.length) {
            int correctIndex = arr[i] - 1;
            if (arr[i] != arr[correctIndex]) {
                swap(arr, i, correctIndex);
            } else {
                i++;
            }
        }
    }

    //max subarray sum with one deletion
    public static int maxSubArraySumWithOneDeletion(int[] arr) {
        int nd = arr[0], d = 0;  //nd=not deleted, d=deleted
        int n = arr.length;
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            d = Math.max(nd, d + arr[i]);
            nd = Math.max(nd, nd + arr[i]);
            max = Math.max(max, Math.max(d, nd));
        }
        return max;
    }


    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //missing number
    public static int missingNumber(int[] arr) {
        int i = 0;
        while (i < arr.length) {
            // numbers started from 0:N
            int correctIndex = arr[i];
            if (correctIndex < arr.length && arr[i] != arr[correctIndex]) {
                swap(arr, i, correctIndex);
            } else {
                i++;
            }
        }
        //find missing
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] != j) return j;
        }

        return arr.length;
    }


    //find duplicate
    public static List<Integer> findDuplicate(int[] arr) {
        List<Integer> result = new ArrayList<>();

        cyclicSort(arr);

        //now add duplicate index in
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != (i + 1)) {
                result.add(arr[i]);
            }
        }
        return result;
    }

    //findSingle duplicate
    public static int findSingleDuplicate(int[] arr) {
        cyclicSort(arr);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != (i + 1)) return arr[i];
        }
        return -1;
    }



}
