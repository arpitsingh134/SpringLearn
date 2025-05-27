package com.java.learn.dsa.lectures;


public class AVL {

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


    public AVL(Node node) {

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

        return rotate(node);
    }


    private Node rotate(Node node) {

        if (height(node.left) - height(node.right) > 1) {
            //left heavy
            //left-left case
            if (height(node.left.left) - height(node.left.right) > 0) {
                return rightRotate(node);
            }
            if (height(node.left.left) - height(node.left.right) < 0) {
                //left-right case
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }

        }

        if (height(node.right) - height(node.left) > 1) {
            //right heavy
            //right-right case
            if (height(node.right.right) - height(node.right.left) > 0) {
                return leftRotate(node);
            }

            if (height(node.right.right) - height(node.right.left) < 0) {
                //right-left case
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }
        return node;
    }

    private Node rightRotate(Node p) {
        Node c = p.left;
        Node t = c.right;
        c.right = p;
        p.left = t;
        p.height = Math.max(height(p.left), height(p.right)) + 1;
        c.height = Math.max(height(c.left), height(c.right)) + 1;
        return c;
    }

    private Node leftRotate(Node p) {
        Node c = p.right;
        Node t = c.left;
        c.left = p;
        p.right = t;
        p.height = Math.max(height(p.left), height(p.right)) + 1;
        c.height = Math.max(height(c.left), height(c.right)) + 1;
        return c;
    }

}
