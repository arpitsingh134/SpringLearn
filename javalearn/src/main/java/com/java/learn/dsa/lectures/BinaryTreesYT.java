package com.java.learn.dsa.lectures;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreesYT {
    static class Node {
        int val;
        Node left;
        Node right;
        public Node(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }


    static class BinaryTree {

        static int index = -1;

        public static Node createTree(int[] nodes) {
            index++;

            if (index >= nodes.length || nodes[index] == -1) {
                return null;
            }

            Node root = new Node(nodes[index]);
            root.left = createTree(nodes);
            root.right = createTree(nodes);
            return root;
        }


        public static void preOrder(Node root) {
            if (root == null) {
                return;
            }
            System.out.print(root.val + " ");
            preOrder(root.left);
            preOrder(root.right);

        }

        public static void postOrder(Node root) {
            if (root == null) {
                return;
            }
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.val + " ");

        }

        public static int diameterOfBT(Node root) {
            if (root == null) {
                return 0;
            }
            int left = diameterOfBT(root.left);
            int right = diameterOfBT(root.right);
            int height = heightNode(root.left) + heightNode(root.right) + 1;
            return Math.max(Math.max(left, right), height);
        }

        public static TreeInfo diameterBT(Node root) {
            if (root == null) {
                return new TreeInfo(0, 0);
            }
            TreeInfo left = diameterBT(root.left);
            TreeInfo right = diameterBT(root.right);

            int myHeight = Math.max(left.height, right.height) + 1;

            int dim1 = left.diameter;
            int dim2 = right.diameter;
            int dim3 = left.height + right.height + 1;

            int myDim = Math.max(Math.max(dim1, dim2), dim3);

            return new TreeInfo(myHeight, myDim);

        }

        static class TreeInfo {
            int height;
            int diameter;

            public TreeInfo(int height, int diameter) {
                this.height = height;
                this.diameter = diameter;
            }
        }

        public static void inOrder(Node root) {
            if (root == null) {
                return;
            }
            inOrder(root.left);
            System.out.print(root.val + " ");
            inOrder(root.right);

        }

        public static int heightNode(Node root) {
            if (root == null) {
                return 0;
            }
            return Math.max(heightNode(root.left), heightNode(root.right)) + 1;
        }

        public static int sumOfNodes(Node root) {
            if (root == null) {
                return 0;
            }
            return root.val + sumOfNodes(root.left) + sumOfNodes(root.right);
        }

        public static int countNodes(Node root) {
            return root == null ? 0 : 1 + countNodes(root.left) + countNodes(root.right);
        }


        public static void printTreeLevelOrder(Node root) {
            if (root == null) {
                return;
            }
            Queue<Node> queue = new LinkedList<>();
            queue.offer(root);
            queue.offer(null);
            while (!queue.isEmpty()) {
                Node node = queue.poll();
                if (node == null) {
                    System.out.println("");
                    if (queue.isEmpty()) {
                        break;
                    }
                    queue.offer(null);
                } else {
                    System.out.print(node.val + " ");
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
            }

        }

        public static boolean isSubtree(Node root, Node subtree) {
            if (subtree == null) {
                return true;
            }
            if (root == null) {
                return false;
            }

            if (root.val == subtree.val) {
                if (isIdentical(root, subtree)) {
                    return true;
                }
            }
            return isSubtree(root.left, subtree) || isSubtree(root.right, subtree);
        }

        public static boolean isIdentical(Node root, Node subtree) {
            if (subtree == null && root == null) {
                return true;
            }
            if (subtree == null || root == null) {
                return false;
            }
            return root.val == subtree.val && isIdentical(root.left, subtree.left) && isIdentical(root.right, subtree.right);
        }


    }


    public static void main(String[] args) {
        Node root = BinaryTree.createTree(new int[]{1, 2, 4, 7, -1, -1, 8, -1, -1, 5, -1, -1, 3, -1, 6, -1, 9, -1, -1});
//        BinaryTree.preOrder(root);
//        System.out.println();
//        BinaryTree.inOrder(root);
////        System.out.println("");
//        BinaryTree.printTreeLevelOrder(root);
        System.out.println("count nodes: " + BinaryTree.countNodes(root));
        System.out.println("sum of nodes: " + BinaryTree.sumOfNodes(root));
        System.out.println("height to tree :" + BinaryTree.heightNode(root));
        System.out.println("diameter to tree :" + BinaryTree.diameterOfBT(root));
        System.out.println("diameter to tree :" + BinaryTree.diameterBT(root).height);


    }


}
