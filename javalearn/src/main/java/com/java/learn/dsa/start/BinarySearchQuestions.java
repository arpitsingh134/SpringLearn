package com.java.learn.dsa.start;

import java.util.Arrays;

public class BinarySearchQuestions {

    public static void main(String[] args) {

    }

    //LC #Ceiling Number arr[index]>target

    public static int binarySearchCeilingNumber(int[] array, int start, int end, int target) {

        if (array[array.length - 1] < target)
            return -1;

        int mid = start + (end - start) / 2;
        if (start >= end) {
            return start;
        }
        if (array[mid] == target) {
            return mid;
        } else if (array[mid] < target) {
            return binarySearchCeilingNumber(array, mid + 1, end, target);
        } else {
            return binarySearchCeilingNumber(array, start, mid - 1, target);
        }
    }

    //LC #Floor arr[index]=< target
    public static int binarySearchFloor(int[] array, int start, int end, int target) {
        if (array[0] > target)
            return -1;
        int mid = start + (end - start) / 2;
        if (start >= end) {
            return end;
        }
        if (array[mid] == target) {
            return mid;
        } else if (array[mid] < target) {
            return binarySearchCeilingNumber(array, mid + 1, end, target);
        } else {
            return binarySearchCeilingNumber(array, start, mid - 1, target);
        }
    }

    //LC #744 find the smallest letter greater then target
    public static char nextGreaterElement(char[] letters, int target) {
        int start = 0, end = letters.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (letters[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return letters[start % letters.length];
    }

    //LC #34 find and last position of element of sorted array

    public int[] searchRange(int[] arr, int target) {
        int[] ans = {-1, -1};
        //fist index
        ans[0] = search(arr, target, true);
        if (ans[0] != -1) {
            ans[1] = search(arr, target, false);
        }
        return ans;
    }

    //orderAgnosticBS
    private static int orderAgnosticBS(int[] arr, int start, int end, int target) {

        boolean isAsc = arr[start] <= arr[end];
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target) {
                return mid;
            }
            if (isAsc) {
                if (arr[mid] > target) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (arr[mid] > target) {
                    end = mid + 1;
                } else {
                    start = mid - 1;
                }
            }
        }
        return -1;
    }

    private static int search(int[] arr, int target, boolean startIndex) {
        int start = 0, end = arr.length - 1;
        int ans = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target) {
                ans = mid;
                if (startIndex) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else if (arr[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return ans;
    }


    public static int infiniteArray(int arr[], int target) {
        //find range
        //start box size of 2 and double later
        int start = 0;
        int end = 1;
        //condition for change box size
        while (target > arr[end]) {
            int temp = end + 1;
            end = end + (end - start + 1) * 2;
            start = temp;
        }
        return Arrays.binarySearch(arr, start, end, target);
    }

    //LC 852/162 Peak Index in a Mountain Array biotic array
    public int peakMountainBS(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] < arr[mid + 1]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }


    //find the pivot in the array --smallest element in rotated sorted array
    private static int findPivot(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] < arr[end]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }


    //LC 36 search in rotated sorted array
    private int searchInRotatedArray(int[] arr, int target) {
        int pivot = findPivot(arr);
        if (pivot == -1) {
            return Arrays.binarySearch(arr, target);
        } else if (arr[pivot] == target) {
            return pivot;
            //right half
        } else if (arr[pivot] <= target && target <= arr[arr.length - 1]) {
            return Arrays.binarySearch(arr, pivot + 1, arr.length - 1, target);
        } else {
            return Arrays.binarySearch(arr, 0, pivot - 1, target);
        }
    }






}
