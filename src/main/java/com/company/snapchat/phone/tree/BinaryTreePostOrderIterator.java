package com.company.snapchat.phone.tree;

import com.fishercoder.common.classes.TreeNode;
import com.fishercoder.common.utils.TreeUtils;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreePostOrderIterator {

  private Deque<TreeNode> stack = new ArrayDeque<>();
  private LinkedList<TreeNode> out = new LinkedList<>();

  // 1. Post-order is the reverse order of pre-order with traversing right substree before traversing
  // left subtree:
  //     pre-order :                             root         -> left-subtree  -> right-subtree
  //     pre-order-visit-right-first:            root         -> right-subtree -> left-subtree
  //     reversed-(pre-order-visit-right-first): left-subtree -> right-subtree -> root
  //     post-order:                             left-subtree -> right-subtree -> root
  //
  // 2. And since stack is LIFO, we should push left substree to stack, then right substree.
  // 3. So for next step, the top element of stack is right subtree.
  // 4. And we need to also reverse the order of the elements poped from stack.
  // 5. So we use another linkedList based stack to maintain the reverse order
  public BinaryTreePostOrderIterator(TreeNode root) {
    stack.addFirst(root);
    while (!stack.isEmpty()) {
      TreeNode node = stack.removeFirst();
      out.addFirst(node);
      if (node.left != null) {
        stack.addFirst(node.left);
      }
      if (node.right != null) {
        stack.addFirst(node.right);
      }
    }
  }

  public boolean hasNext() {
    return !out.isEmpty();
  }

  public TreeNode next() {
    if (!hasNext()) {
      return null;
    }
    return out.removeFirst();
  }

  public static void main(String[] args) {

    List<Integer> list = Arrays.asList(new Integer[] {5, 3, 6, 2, 4, null, null, 1});
    /**
     * the tree for [5, 3, 6, 2, 4, null, null, 1], i.e. looks like the following:
            5
          /   \
         3     6
        / \    / \
       2   4  #   #
      /
     1
     */
    TreeNode root = TreeUtils.constructBinaryTree(list);
    List<Integer> result = new ArrayList<>();
    BinaryTreePostOrderIterator iterator = new BinaryTreePostOrderIterator(root);
    while (iterator.hasNext()) {
      result.add(iterator.next().val);
    }
    System.out.println(result.toString()); // [1, 2, 4, 3, 6, 5]
  }
}
