package com.java.learn.dsa.start.BTQuestions;

import java.util.*;

public class Lec1BTQues {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(10);
        System.out.println(list);
        list.add(0, 30);
        System.out.println(list);

    }

    public List<List<Integer>> levelOrderOne(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        Queue<TreeNode> q = new LinkedList<>();

        if (root == null) {
            return res;
        }

        List<Integer> currentList = new ArrayList<>();

        q.offer(root);
        q.offer(null);
        while (!q.isEmpty()) {
            TreeNode temp = q.poll();
            if (temp != null) {
                currentList.add(temp.val);
                if (temp.left != null) {
                    q.offer(temp.left);
                }
                if (temp.right != null) {
                    q.offer(temp.right);
                }
            } else {
                List<Integer> tempList = new ArrayList<>(currentList);
                res.add(tempList);
                currentList.clear();
                if (!q.isEmpty()) q.offer(null);
            }
        }

        return res;
    }


    //102. Binary Tree Level Order Traversal
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        Queue<TreeNode> q = new LinkedList<>();
        if (root == null) {
            return res;
        }
        q.offer(root);
        while (!q.isEmpty()) {
            List<Integer> currentList = new ArrayList<>();
            int level = q.size();
            for (int i = 0; i < level; i++) {
                TreeNode temp = q.poll();
                currentList.add(temp.val);
                if (temp.left != null) {
                    q.offer(temp.left);
                }
                if (temp.right != null) {
                    q.offer(temp.right);
                }
            }
            res.add(currentList);
        }
        return res;
    }

    //https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        Queue<TreeNode> q = new LinkedList<>();
        if (root == null) {
            return res;
        }
        q.offer(root);
        while (!q.isEmpty()) {
            List<Integer> currentList = new ArrayList<>();
            int level = q.size();
            for (int i = 0; i < level; i++) {
                TreeNode temp = q.poll();
                currentList.add(temp.val);
                if (temp.left != null) {
                    q.offer(temp.left);
                }
                if (temp.right != null) {
                    q.offer(temp.right);
                }
            }
            res.add(0, currentList);
        }
        return res;
    }


    //https://leetcode.com/problems/average-of-levels-in-binary-tree/
    public List<Double> averageOfLevelsOne(TreeNode root) {
        List<Double> res = new ArrayList<>();

        Queue<TreeNode> q = new LinkedList<>();
        if (root == null) {
            return res;
        }
        q.offer(root);
        q.offer(null);

        double sum = 0, count = 0;

        while (!q.isEmpty()) {
            TreeNode temp = q.poll();
            if (temp != null) {
                sum += temp.val;
                count++;
                if (temp.left != null) {
                    q.offer(temp.left);
                }
                if (temp.right != null) {
                    q.offer(temp.right);
                }
            } else {
                res.add(sum / count);
                sum = 0;
                count = 0;
                if (!q.isEmpty()) q.offer(null);
            }
        }
        return res;
    }


    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();

        Queue<TreeNode> q = new LinkedList<>();
        if (root == null) {
            return res;
        }
        q.offer(root);
        double sum = 0, count = 0;

        while (!q.isEmpty()) {
            List<Integer> currentList = new ArrayList<>();
            int level = q.size();
            for (int i = 0; i < level; i++) {
                TreeNode temp = q.poll();
                sum += temp.val;
                if (temp.left != null) {
                    q.offer(temp.left);
                }
                if (temp.right != null) {
                    q.offer(temp.right);
                }
            }
            res.add(sum / (level * 1.0));
            sum = 0;
        }
        return res;
    }


    public TreeNode levelOrderSuccessor(TreeNode root, int value) {
        if (root == null) return null;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int level = q.size();
            for (int i = 0; i < level; i++) {
                TreeNode temp = q.poll();
                if (temp.left != null) {
                    q.offer(temp.left);
                }
                if (temp.right != null) {
                    q.offer(temp.right);
                }
                if (value == temp.val) {
                    return q.peek();
                }
            }
        }
        return q.peek();
    }


    public List<List<Integer>> zigzagLevelOrderOne(TreeNode root) {

        List<List<Integer>> res = new ArrayList<>();

        Deque<TreeNode> q = new LinkedList<>();
        if (root == null) {
            return res;
        }
        q.offer(root);
        boolean flag = false;
        while (!q.isEmpty()) {
            List<Integer> currentList = new ArrayList<>();
            int level = q.size();
            for (int i = 0; i < level; i++) {

                if (!flag) {
                    TreeNode temp = q.pollFirst();
                    currentList.add(temp.val);
                    if (temp.left != null) {
                        q.addLast(temp.left);
                    }
                    if (temp.right != null) {
                        q.addLast(temp.right);
                    }
                } else {
                    TreeNode temp = q.pollLast();
                    currentList.add(temp.val);
                    if (temp.right != null) {
                        q.addFirst(temp.right);
                    }
                    if (temp.left != null) {
                        q.addFirst(temp.left);
                    }
                }
            }
            res.add(currentList);
            flag = !flag;
        }
        return res;
    }


    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> res = new ArrayList<>();

        Deque<TreeNode> q = new LinkedList<>();
        if (root == null) {
            return res;
        }
        q.offer(root);
        boolean flag = false;
        while (!q.isEmpty()) {
            LinkedList<Integer> currentList = new LinkedList<>();
            int level = q.size();
            for (int i = 0; i < level; i++) {

                TreeNode temp = q.pollFirst();
                if (!flag) {
                    currentList.addLast(temp.val);
                } else {
                    currentList.addFirst(temp.val);
                }

                if (temp.left != null) q.addLast(temp.left);
                if (temp.right != null) q.addLast(temp.right);
            }
            res.add(currentList);
            flag = !flag;
        }
        return res;
    }


    //https://leetcode.com/problems/populating-next-right-pointers-in-each-node/description/
    public Node connectOne(Node node) {
        Queue<Node> q = new LinkedList<>();
        if (node == null) {
            return node;
        }
        node.next = null;
        q.offer(node);
        while (!q.isEmpty()) {
            int level = q.size();
            Node prev = null;
            for (int i = 0; i < level; i++) {
                Node curr = q.poll();
                if (prev != null) {
                    prev.next = curr;
                }
                prev = curr;
                if (curr.left != null) q.offer(curr.left);
                if (curr.right != null) q.offer(curr.right);
            }
        }

        return node;
    }

    //o(1)
    public Node connect(Node node) {
        if (node == null) {
            return node;
        }
        Node leftMost = node;

        while (leftMost.left != null) {
            Node curr = leftMost;

            while (curr != null) {
                curr.left.next = curr.right;
                if (curr.next != null) curr.right.next = curr.next.left;
                curr = curr.next;
            }

            leftMost = leftMost.left;

        }

        return node;
    }


    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) return false;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int level = q.size();
            boolean foundX = false, foundY = false;

            for (int i = 0; i < level; i++) {
                TreeNode temp = q.poll();

                // Check if x and y are siblings
                if (temp.left != null && temp.right != null) {
                    int l = temp.left.val;
                    int r = temp.right.val;
                    if ((l == x && r == y) || (l == y && r == x)) {
                        return false; // they are siblings, not cousins
                    }
                }

                if (temp.left != null) {
                    q.offer(temp.left);
                    if (temp.left.val == x) foundX = true;
                    if (temp.left.val == y) foundY = true;
                }

                if (temp.right != null) {
                    q.offer(temp.right);
                    if (temp.right.val == x) foundX = true;
                    if (temp.right.val == y) foundY = true;
                }
            }

            if (foundX && foundY) return true;   // cousins
            if (foundX || foundY) return false;  // found only one
        }

        return false;
    }

    int diameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        height(root);
        return diameter;
    }

    private int height(TreeNode node) {
        if (node == null) return 1;
        int left = height(node.left);
        int right = height(node.right);

        Math.max(left + right, diameter);

        return Math.max(left, right) + 1;
    }

    private int heightOne(TreeNode node) {
        return node != null ? Math.max(height(node.left), height(node.right)) + 1 : 0;
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        root.left = right;
        root.right = left;

        return root;

    }


    public void flatten(TreeNode root) {

        if (root == null) return;

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = null;

        flatten(left);
        flatten(right);

        root.right = left;

        TreeNode curr = root;

        while (curr.right != null) curr = curr.right;
        curr.right = right;

    }

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode node, long min, long max) {
        if (node == null) return true;

        if (node.val <= min || node.val >= max) return false;

        return isValidBST(node.left, min, node.val) &&
                isValidBST(node.right, node.val, max);
    }




    /*
    1. Normal preorder traversal
    2. As you traverse store in queue
    3. In the end , remove from queue and create linked list
     */

    public void flattenOne(TreeNode root) {

        if (root == null) return;

        Queue<TreeNode> queue = new LinkedList<>();
        inOrderTraversal(root, queue);
        System.out.println(queue);
        TreeNode curr, prev = null;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            if (curr != null && prev != null) {
                prev.right = curr;
                prev.left = null;
            }
            prev = curr;
        }

    }

    public void inOrderTraversal(TreeNode node, Queue<TreeNode> queue) {

        if (node == null) return;
        queue.offer(node);
        inOrderTraversal(node.left, queue);
        inOrderTraversal(node.right, queue);

    }


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;

        if (root == q || root == p) {
            return root;
        }
        //left search
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        //right search
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        //if found one ans in right and one ans left
        if (left != null && right != null)
            return root;

        return left == null ? right : left;

    }


    int count = 0;

    public int kthSmallest(TreeNode root, int k) {
        return helper(root, k).val;
    }

    public TreeNode helper(TreeNode root, int k) {
        if (root == null) return null;
        helper(root.left, k);
        count++;
        if (k == count) {
            return root;
        }
        return helper(root.right, k);
    }


    int pre = 0; // global preorder index

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        pre = 0; // reset before starting
        return helperBuildTree(preorder, inorder, 0, inorder.length - 1);
    }

    public TreeNode helperBuildTree(int[] preorder, int[] inorder, int start, int end) {
        if (start > end || pre >= preorder.length) return null;

        TreeNode node = new TreeNode(preorder[pre++]);

        // Find index of current root in inorder
        int index = start;
        for (int i = start; i < end; i++) {
            if (inorder[i] == preorder[pre]) {
                index = i;
                break;
            }
        }

        node.left = helperBuildTree(preorder, inorder, start, index - 1);
        node.right = helperBuildTree(preorder, inorder, index + 1, end);

        return node;
    }


    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (targetSum == root.val && root.left == null && root.right == null) return true;
        int sum = targetSum - root.val;
        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }

    int finalSum = 0;

    public int sumNumbers(TreeNode root) {
        return helperSumNumbers(root, 0);
    }

    private int helperSumNumbers(TreeNode root, int sum) {

        if (root == null) return 0;


        //leaf node return update finalSum
        sum = sum * 10 + root.val;

        if (root.left == null && root.right == null) {
            return sum;
        }
        return helperSumNumbers(root.left, sum) + helperSumNumbers(root.right, sum);
    }

    int ansMaxPath = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        helperMaxPathSum(root);
        return ansMaxPath;
    }

    int helperMaxPathSum(TreeNode root) {
        if (root == null)
            return 0;

        int left = helperMaxPathSum(root.left);
        int right = helperMaxPathSum(root.right);

        left = Math.max(0, left);
        right = Math.max(0, right);

        int pathSum = left + right + root.val;

        ansMaxPath = Math.max(pathSum, ansMaxPath);
        return Math.max(left, right) + root.val;

    }


    boolean isBalance(String str) {
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        Stack<Character> stack = new Stack<>();
        for (char s : str.toCharArray()) {
            if (map.containsValue(s)) {
                stack.push(s);
            } else if (map.containsKey(s)) {
                if (stack.isEmpty() || stack.pop() != map.get(s)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }


    public List<List<Integer>> verticalTraversal(TreeNode root) {
        return helperVerticalTraversal(root);
    }

    private List<List<Integer>> helperVerticalTraversal(TreeNode root) {

        if (root == null) return null;

        Map<Integer, List<Integer>> map = new HashMap<>();
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

        Queue<Pair> queue = new LinkedList<>();

        queue.offer(new Pair(root, 0));
        while (!queue.isEmpty()) {
            int level = queue.size();
            for (int i = 0; i < level; i++) {
                Pair pair = queue.poll();
                if (pair != null) {
                    List<Integer> list = map.getOrDefault(pair.column, new ArrayList<>());
                    list.add(pair.value.val);
                    map.put(pair.column, list);
                }
                if (pair != null) {
                    max = Math.max(pair.column, max);
                    min = Math.min(pair.column, min);
                    if (pair.value.left != null) queue.offer(new Pair(pair.value.left, pair.column - 1));
                    if (pair.value.right != null) queue.offer(new Pair(pair.value.right, pair.column + 1));
                }
            }
        }

        List<List<Integer>> res = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            List<Integer> list = map.get(i);
            Collections.sort(list, Comparator.reverseOrder());
            res.add(list);
        }

        return res;


    }

    private class Pair {
        TreeNode value;
        int column;

        Pair(TreeNode value, int column) {
            this.value = value;
            this.column = column;
        }
    }

    //word ladder : 126
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList); // Fix 1: Convert to HashSet
        if (!wordSet.contains(endWord))
            return 0;

        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        wordSet.remove(beginWord); // Fix 3: Mark beginWord as visited
        int counter = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            counter++;
            for (int i = 0; i < size; i++) {

                String current = q.poll();

                //this block of code form all combinations of word which differ by one char at every place
            /*
                hit --> ait,bit,cit,----zit
                hit--> hat,hbt,hct---hzt
                hit--> hia,hib----hiz
             */
                for (int j = 0; j < current.length(); j++) { // Fix 2: Removed Objects.requireNonNull
                    char[] temp = current.toCharArray();
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        temp[j] = ch;
                        String newWord = new String(temp);

                        if (newWord.equals(endWord))
                            return counter + 1;

                        if (wordSet.contains(newWord)) { // Fix 4: Single check instead of two
                            q.offer(newWord);
                            wordSet.remove(newWord); // Fix 4: Remove from wordSet (acts as visited)
                        }
                    }
                }
            }

            //end of loop
        }
        return 0;
    }


    public boolean findTarget(TreeNode root, int k) {
        return helperFindTarget(root,k,new HashSet<>());
    }

    private boolean helperFindTarget(TreeNode root, int k, Set<Integer> set) {

        if(root==null) return false;

        if(set.contains(k-root.val)){
            return true;
        }
        set.add(root.val);
        //check left
        if (root.left != null) helperFindTarget(root.left, k, set);

        //check right
        if (root.right != null) helperFindTarget(root.right, k, set);

        return false;
    }


}

