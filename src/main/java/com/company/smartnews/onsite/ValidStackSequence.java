package com.company.smartnews.onsite;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.ArrayDeque;
import java.util.Deque;

public class ValidStackSequence {

  /**
   * C:
   * <p>What is the data type of input and output? array and boolean?</p>
   * <p>What if we meet a null as input? </p>
   * <p>Can we assume the length of popped and pushed are the same? And how long they can be? </p>
   * <p>What is the value range if the element in the sequences</p>
   * A:
   * <p>The input can be null, thus return false</p>
   * <p>The length of popped and pushed sequences can be different, if popped is longer than pushed,
   * return false</p>
   * <p>No duplicate element in the sequences</p>
   * R:
   * <p> Use a stack to simulate the pushed and popped sequence.
   * <p> Use a pointer popCount points at the head of popped sequence initially, the semantic of the
   * pointer is : the left side of the pointer is the valid popped sequence we have been checked.
   * </p>
   * <p> if the pop count is equal to the length of popped sequence, the popped sequence is
   * valid</p>
   * T:
   * <p>pushed:1,2,3, popped: 1,2,3 => true, 3,2,1=> true, 3,1,2 => false, null => false, </p>
   */
  public static boolean isValidStachSequence(int[] pushed, int[] popped) {
    if (pushed == null || popped == null || pushed.length < popped.length) {
      return false;
    }
    Deque<Integer> stack = new ArrayDeque<>();
    int popCount = 0;
    for (int i = 0; i < pushed.length; i++) {
      stack.addLast(pushed[i]);
      while (!stack.isEmpty() && popCount < popped.length && stack.peekLast() == popped[popCount]) {
        stack.pollLast();
        popCount++;
      }
    }
    return popCount == popped.length;
  }

  public static void main(String[] args) {
    int[] pushed1 = new int[]{1, 2, 3};
    int[] pushed2 = new int[]{1, 2, 3, 4, 5, 6};
    int[] popped1 = new int[]{1, 2, 3};
    int[] popped2 = new int[]{3, 2, 1};
    int[] popped3 = null;
    int[] popped4 = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
    int[] popped5 = new int[]{3, 1, 2};

    assertTrue(isValidStachSequence(pushed1, popped1));
    assertTrue(isValidStachSequence(pushed1, popped2));
    assertTrue(isValidStachSequence(pushed2, popped1));
    assertTrue(isValidStachSequence(pushed2, popped2));

    assertFalse(isValidStachSequence(pushed1, popped3));
    assertFalse(isValidStachSequence(pushed1, popped5));
    assertFalse(isValidStachSequence(pushed1, popped4));

  }
}
