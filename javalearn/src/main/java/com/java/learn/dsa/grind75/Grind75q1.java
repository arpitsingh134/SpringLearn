package com.java.learn.dsa.grind75;

import java.util.Stack;

public class Grind75q1 {

    public static void main(String[] args) {
        Grind75q1 gr = new Grind75q1();
//        String s = "A man, a plan, a canal: Panama";
        String s = "abccccdd";
        System.out.println(gr.longestPalindrome(s));
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


    public boolean hasCycle(ListNode head) {

        if (head == null) return false; // Fix: empty list can't have a cycle

        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next != null) {
            if (slow == fast) return true;
            slow = slow.next;
            fast = fast.next.next;
        }

        return false;
    }


    private boolean isBadVersion(int version) {
        return false;
    }

    public int firstBadVersion(int n) {
        int left = 0, right = n;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (isBadVersion(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }


    public boolean canConstruct(String ransomNote, String magazine) {

        int count[] = new int[26];

        for (int i = 0; i < ransomNote.length(); i++) {
            count[ransomNote.charAt(i) - 'a']++;
        }
        for (int i = 0; i < magazine.length(); i++) {
            if (count[magazine.charAt(i) - 'a'] > 0) {
                count[magazine.charAt(i) - 'a']--;
            }
        }

        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                return false;
            }
        }

        return true;
    }


    public int climbStairs(int n) {
        int dp[] = new int[n + 1];
        return dpClimbStairs(n, dp);
    }

    private int dpClimbStairs(int n, int[] dp) {
        if (n < 0) return 0;
        if (n == 0) {
            dp[0] = 1;
            return 1;
        }
        if (dp[n] != 0) return dp[n];
        int num1 = dpClimbStairs(n - 1, dp);
        int num2 = dpClimbStairs(n - 2, dp);
        dp[n] = num1 + num2;
        return num1 + num2;
    }


    public int longestPalindrome(String s) {

        int twos = 0, ones = 0;
        int[] hash = new int[256];
        for (int i = 0; i < s.length(); i++) {
            hash[s.charAt(i)]++;
        }


        for (int i = 0; i < hash.length; i++) {
            if (hash[i] != 0) {
                twos += hash[i] / 2;
                ones += hash[i] % 2;
            }
        }

        System.out.println(twos + ":::" + ones);
        return (2 * twos) + (ones >= 1 ? 1 : 0);

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


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null)
            return null;

        int curr = root.val;

        if (curr < p.val && curr < q.val) {
            return lowestCommonAncestor(root.right, p, q); // go right
        }
        if (curr > p.val && curr > q.val) {
            return lowestCommonAncestor(root.left, p, q); // go left
        }

        // If one value is on the left and the other is on the right, root is LCA
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


    public boolean isBalanced(TreeNode root) {

        return dfsHeight(root) != -1;

    }

    private int dfsHeight(TreeNode root) {


        if (root == null) return 0;

        int left = dfsHeight(root.left);
        if (left == -1) return -1;
        int right = dfsHeight(root.right);
        if (right == -1) return -1;
        if (Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;

    }


    public ListNode reverseList(ListNode head) {

        ListNode prev = null;
        ListNode curr = head;
        ListNode next = null;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
        return head;
    }


    public ListNode reverseListRecursive(ListNode head) {
        // Base case: if the head is null or only one element is left
        if (head == null || head.next == null) {
            return head;
        }

        // Recursive case: reverse the rest of the list
        ListNode reversedListHead = reverseListRecursive(head.next);

        // Adjust pointers to reverse the direction
        head.next.next = head;
        head.next = null;

        return reversedListHead;
    }


    //https://leetcode.com/problems/maximum-subarray/description/
    public int maxSubArraySum(int[] arr) {

        if (arr == null || arr.length == 0) return 0;

        int maxSum = Integer.MIN_VALUE, sum = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            maxSum = Math.max(maxSum, sum);
            sum = Math.max(sum, 0);
        }
        return maxSum;

    }

    private boolean isOverlapped(int s1, int e1, int s2, int e2) {
        return Math.max(s1, s2) < Math.min(e1, e2);
    }


}

