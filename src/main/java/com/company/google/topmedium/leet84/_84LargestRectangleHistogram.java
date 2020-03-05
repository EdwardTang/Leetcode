package com.company.google.topmedium.leet84;

import static org.junit.Assert.assertEquals;

import java.util.Deque;
import java.util.LinkedList;

public class _84LargestRectangleHistogram {

  /**
   * Leetcode 84: Largest Rectangle in histograms
   *
   * <p>Given n non-negative integers representing the histogram's bar height where the width of
   * each bar is 1, find the area of largest rectangle in the histogram.
   *
   * <p>Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3]. The largest
   * rectangle is shown in the shaded area, which has area = 10 unit.
   *
   * <p>For example, Given heights = [2,1,5,6,2,3], return 10.
   *
   * <p>C:
   *
   * <p>What is maximum area supported in this quesiont? Within Integer' data range
   *
   * <p>Is there any negative height? A:
   *
   * <p>assume the maximum area is less than Integer.MAX_VALUE
   *
   * <p>R:
   *
   * <p>Use a stack to maintain a series of monolithtic increasing histrograms's index. Only
   * monolithtic increasing histogram can produce more rectangle area.
   *
   * <p>When we meet decrease histogram: heights[i - 1] > heights[i], pop the stack top, and
   * calculate the area with the following formula: area = (i - stock[top - 1] - 1) *
   * heights[stack[top]] T:
   *
   * <p>null, empty -> 0
   *
   * <p>[1,2,3,4] -> 6
   *
   * <p>[1,2,3,4,3] -> 9
   *
   * <p>[3,2,1] -> 4
   */
  public static int largestRectangleArea(int[] heights) {
    if (heights == null || heights.length == 0) {
      return 0;
    }
    Deque<Integer> stack = new LinkedList<>();
    int maxArea = 0;
    for (int i = 0; i <= heights.length; i++) {
      int cur = (i == heights.length) ? 0 : heights[i];
      while (!stack.isEmpty() && heights[stack.peekLast()] >= cur) {
        int height = heights[stack.pollLast()];
        int left = stack.isEmpty() ? 0 : stack.peekLast() + 1;
        maxArea = Math.max(maxArea, height * (i - left));
      }
      stack.addLast(i);
    }
    return maxArea;
  }

  public static void main(String[] args) {
    assertEquals(0, largestRectangleArea(null));
    assertEquals(0, largestRectangleArea(new int[] {}));
    assertEquals(1, largestRectangleArea(new int[] {1}));
    assertEquals(6, largestRectangleArea(new int[] {1, 2, 3, 4}));
    assertEquals(9, largestRectangleArea(new int[] {1, 2, 3, 4, 3}));
    assertEquals(4, largestRectangleArea(new int[] {3, 2, 1}));
  }
}
