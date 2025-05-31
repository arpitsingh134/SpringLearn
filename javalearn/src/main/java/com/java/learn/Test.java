package com.java.learn;

public class Test {

    public static void main(String[] args) {

        int arr[] = new int[]{1,2,3,4,500};
        System.out.println(maxSubArray(arr, 500));
    }

    //count subarray product less then  k
    public static int maxSubArray(int[] arr, int k) {


        if (k == 0) return 0;

        int count = 0, sum = 1;
        int left = 0, right = 0;
        while (arr.length > right ) {

            sum *= arr[right];
            if (left == right) {
                if (sum <= k) {
                    ++count;
                }
                right++;
            } else if (sum <= k) {
                //expend
                right++;
                count++;
            }
            if (sum > k || right >= arr.length) {
                sum = 1;
                left++;
                right = left;
            }
        }
        return count;
    }









}
