package com.java.learn.dsa.start;


public class SegmentTreeDemo {

    public static void main(String[] args) {
        int[] arr = {3, 8, 6, 7, -2, -8, 4, 9};

        SegmentTree tree = new SegmentTree(arr);
//        tree.display();

        System.out.println(tree.query(2,6));
        tree.update(3,14);
//        tree.display();

        System.out.println(tree.query(2,6));

    }


    private static class SegmentTree {
        private static class Node {
            int data;
            int start;
            int end;
            Node left;
            Node right;

            public Node(int start, int end) {
                this.start = start;
                this.end = end;
            }
        }

        Node root;

        public SegmentTree(int[] arr) {
            this.root = createTree(arr, 0, arr.length - 1);
        }

        private Node createTree(int[] arr, int start, int end) {

            if (start == end) {
                Node leaf = new Node(start, end);
                leaf.data = arr[start];
                return leaf;
            }

            //create new node with index you are at
            Node node = new Node(start, end);
            int mid = start + (end - start) / 2;

            //left tree call
            node.left = createTree(arr, start, mid);
            //right tree call
            node.right = createTree(arr, mid + 1, end);

            //finally add right+left
            node.data = node.left.data + node.right.data;
            return node;
        }

        public void display() {
            display(this.root);
        }

        private void display(Node node) {

            String str = "";
            if (node.left != null) {
                str = str + "Interval=[" + node.left.start + "-" + node.left.end + "] and data :" + node.left.data + "=>";
            } else {
                str = str + "No left Child";
            }
            //for current node
            str = str + "Interval=[" + node.start + "-" + node.end + "] and data :" + node.data + "<=";

            if (node.right != null) {
                str = str + "Interval=[" + node.right.start + "-" + node.right.end + "] and data :" + node.right.data;
            } else {
                str = str + "No Right Child";
            }

            System.out.println(str+"\n");

            //call recursion

            if (node.left != null) {
                display(node.left);
            }

            if (node.right != null) {
                display(node.right);
            }

        }


        public int query(int qs, int qe) {
            return this.query(this.root, qs, qe);
        }

        private int query(Node node, int qs, int qe) {

            //case 1 return node value
            if (node.start >= qs && node.end <= qe) {
                //completely inside query    qs-----node [start,end]---------qe
                return node.data;
            }
            //case 2 return default value
            else if (node.start > qe || node.end < qs) {
                //completely outside of node       qs---------qe -<--- ns------ne || ----- ns------ne-<- qs---------qe
                return 0;
            }
            //case 3 call recursion and add left+ right where we use case 1 and 2
            else {
                //call recursion
                return this.query(node.left, qs, qe) + this.query(node.right, qs, qe);
            }
        }

        public void update(int index, int value) {
            this.root.data = update(root, index, value);
        }

        private int update(Node node, int index, int value) {
            if (index >= node.start && index <= node.end) {
                //leaf
                if (index == node.start && index == node.end) {
                    node.data = value;
                    return node.data;
                } else {
                    //call recursion
                    int leftAns = update(node.left, index, value);
                    int rightAns = update(node.right, index, value);
                    node.data = leftAns + rightAns;
                    return node.data;
                }
            }
            return node.data;
        }


    }

}
