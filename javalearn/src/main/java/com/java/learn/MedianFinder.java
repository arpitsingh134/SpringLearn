package com.java.learn;


public class MedianFinder {

    int median = 0;
    int size = 0;
    int sum = 0;
    TreeNode node;

    private void addValue(int value) {
        if (node == null) {
            node = new TreeNode(value);
            sum += value;
            ;
        }
        node = addValueNodeInTree(node, value);
    }

    public TreeNode addValueNodeInTree(TreeNode node, int value) {
        int v = node.val;

        if (v < node.val) {
            if (node.left == null) {
                node.left = new TreeNode(v);
                size++;
                sum += value;
                return node;
            }
            return addValueNodeInTree(node.left, value);
        } else {

            if (node.right == null) {
                node.right = new TreeNode(v);
                sum += value;
                return node;
            }
            return addValueNodeInTree(node.right, value);
        }

    }

    public int findMedian() {
        if (size != 0) median = sum / size;
        return median;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int v) {
            this.val = v;
            left = null;
            right = null;
        }
    }

}
