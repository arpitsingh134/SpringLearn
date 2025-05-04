package com.java.learn.grind75;

import java.util.Stack;

public class Grind75q1 {

    public static void main(String[] args) {
        Grind75q1 gr = new Grind75q1();
        String s = "A man, a plan, a canal: Panama";
        System.out.println(gr.isPalindrome(s));
    }

    //https://leetcode.com/problems/two-sum/
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    //https://leetcode.com/problems/valid-parentheses/
    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();

        char[] charArray = s.toCharArray();
        for (char c : charArray) {

            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    Character top = stack.peek();
                    if ((top == '(' && c == ')') || (top == '{' && c == '}') || (top == '[' && c == ']')) {
                        stack.pop();
                    } else {
                        return false;
                    }
                }
            }
        }

        return stack.isEmpty();

    }


    //https://leetcode.com/problems/merge-two-sorted-lists/
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1.val <= list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }

    }

    private class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
    public int maxProfit(int[] prices) {
        int mof = 0, min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else {
                int cp = prices[i] - min;
                mof = Math.max(mof, cp);
            }
        }
        return mof;
    }

    //https://leetcode.com/problems/valid-palindrome/
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        StringBuilder str = new StringBuilder();
        System.out.println("str" + str);
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                str.append(ch);
            }
        }
        StringBuilder revs = new StringBuilder(str);
        revs.reverse();
        return str.toString().equals(revs.toString());
    }

    public boolean isPalindrometwo(String s) {
        s = s.toLowerCase();
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            // Move left pointer to the next alphabetic character
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            // Move right pointer to the previous alphabetic character
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            // Compare characters at left and right pointers
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] count = new int[26]; // Only lowercase English letters

        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }

        for (int freq : count) {
            if (freq != 0) return false;
        }

        return true;
    }


    public int search(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                return end = mid - 1;
            } else {
                start = mid + 1;
            }

        }
        return -1;
    }


    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int originalColor = image[sr][sc];
        if (originalColor != color) {
            fill(image, sr, sc, originalColor, color);
        }
        return image;
    }

    public void fill(int[][] image, int sr, int sc, int originalColor, int newColor) {
        if (sr < 0 || sr > image.length - 1 || sc < 0 || sc > image[sr].length - 1)
            return;
        if (image[sr][sc] != originalColor)
            return;

        image[sr][sc] = newColor;
        fill(image, sr, sc++, originalColor, newColor);
        fill(image, sr, sc--, originalColor, newColor);
        fill(image, sr++, sc, originalColor, newColor);
        fill(image, sr--, sc, originalColor, newColor);
    }

    public TreeNode invertTreePreOrder(TreeNode root) {

        if (root == null) return null;

        invertTreePreOrder(root.left);
        invertTreePreOrder(root.right);

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;


        return root;

    }

    public TreeNode invertTreeDFC(TreeNode root) {

        if (root == null) return null;

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTreeDFC(root.left);
        invertTreeDFC(root.right);

        return root;

    }


    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}







