package com.java.learn.dsa.grind75;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Week1 {

    private int diameterBT = 0;

    public static void main(String[] args) {
        //[[1,2,3,1]]

        System.out.println(~(-6));
        System.out.println(~(5));
        System.out.println(-6);

//        Week1 week1 = new Week1();
//        boolean flag = week1.containsDuplicate(new int[]{1, 2, 3, 4});
//        System.out.println(flag);

    }


    public boolean isSameTree(TreeNode p, TreeNode q) {

        if (p == null && q == null) {
            return true;
        } else if (p == null) {
            return false;
        } else if (q == null) {
            return false;
        } else {
            return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }


    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            return root.left.val == root.right.val && isSymmetric(root.left) && isSymmetric(root.right);
        }
    }


    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i : nums) {
            res ^= i;
        }
        return res;
    }

    public boolean isPalindrome(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode reverse = reverse(head);
        while (reverse != null) {
            if (reverse.val != head.val) {
                return false;
            }
            reverse = reverse.next;
            head = head.next;
        }
        return true;
    }

    private ListNode reverse(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }


    //Moor's voting algorithm
    public int majorityElement(int[] nums) {

        int element = nums[0];
        int count = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == element) {
                count++;
            } else if (count > 0) {
                count--;
            }
            if (count == 0) {
                element = nums[i];
                count = 1;
            }
        }

        if (count != 0) {
            return element;
        } else return -1;

    }

    public String addBinary(String a, String b) {
//        int num1 = Integer.parseInt(a, 2);
//        int num2 = Integer.parseInt(b, 2);
//        return Integer.toBinaryString(num1 + num2);


        int i = a.length() - 1;
        int j = b.length() - 1;

        int carry = 0;

        StringBuilder sb = new StringBuilder();
        while (i >= 0 && j >= 0) {
            int digitA = (i >= 0) ? a.charAt(i--) - '0' : 0;
            int digitB = (j >= 0) ? b.charAt(j--) - '0' : 0;
            int sum = digitA + digitB + carry;
            sb.append(sum % 10);
            carry = sum / 10;

        }
        return sb.reverse().toString();
    }

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return diameterBT;
    }

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (!seen.add(num)) {
                return true; // duplicate found
            }
        }
        return false; // all unique
    }

    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);

        diameterBT = Math.max(diameterBT, left + right);
        return Math.max(left, right) + 1; //height of bt
    }

    public ListNode middleNode(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1; //height of bt
    }


    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int result = 0;


        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {

                if (i < s.length() - 1) {
                    if (map.get(s.charAt(i)) < map.get(s.charAt(i + 1))) {
                        result += map.get(s.charAt(i + 1)) - map.get(s.charAt(i));
                        i++;
                    } else {
                        result += map.get(s.charAt(i));
                    }
                } else {
                    result += map.get(s.charAt(i));
                }
            }
        }
        return result;
    }


    public boolean backspaceCompare(String s, String t) {
        int i = s.length() - 1, j = t.length() - 1;

        while (i >= 0 && j >= 0) {

            char s1 = s.charAt(i);
            char s2 = t.charAt(j);


            if (s1 != s2) {
                if (s1 == '#') {
                    i--;
                } else if (s2 == '#') {
                    j--;
                } else {
                    return false;
                }
            } else if (s1 == '#') {
                j -= 2;
                i -= 2;
            } else {
                j--;
                i--;
            }
        }
        return true;
    }

}



