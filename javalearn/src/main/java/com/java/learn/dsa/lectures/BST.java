package com.java.learn.dsa.lectures;

public class BST {

    private Node root;

    private static class Node {
        int val;
        Node left;
        Node right;
        int height;


        public Node(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
            this.height = 0;
        }

        public int getVal() {
            return val;
        }

    }


    public BST(Node node) {

        this.root = node;
    }

    public int height(Node node) {
        if (node == null) {
            return -1;
        }
        return node.height;
    }

    public boolean isBalanced(Node node) {
        if (node == null) return true;
        return Math.abs(height(node.left) - height(node.right)) <= 1 && isBalanced(node.right) && isBalanced(node.left);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void display() {
        display(root, "Root Node : ");
    }

    private void display(Node node, String s) {
        if (node == null) {
            return;
        }
        System.out.println(s + node.getVal());
        display(node.left, "Left child of :" + node.val + " : ");
        display(node.right, "Right child of :" + node.val + " : ");

    }

    public void Node(int value) {
        root = insert(value, root);
    }

    public Node insert(int value, Node node) {
        if (node == null) {
            return new Node(value);
        }
        if (value < node.val) {
            node.left = insert(value, node.left);
        }
        if (value > node.val) {
            node.right = insert(value, node.right);
        }

        node.height = Math.max(height(node.left), height(node.right)) + 1;

        return node;
    }

}
