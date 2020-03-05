package com.company.google.topmedium.leet84.uptake;


import static com.company.google.topmedium.leet84._84LargestRectangleHistogram.largestRectangleArea;
import static org.junit.Assert.assertEquals;

public class _85MaxRectangleMatrix {

  /**
   * 85. Maximal Rectangle
   *
   * <p>Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only
   * 1's and return its area.
   *
   * <p>For example, given the following matrix:
   *
   * <p>1 0 1 0 0
   *    1 0 1 1 1
   *    1 1 1 1 1
   *    1 0 0 1 0
   *
   *    Return 6.
   * <p>C:
   *
   * <p>What is the data type of the elements? char or int? -> char
   *
   * <p>How big can the matrix be? The area of the matrix can be fit into memory A:
   *
   * <p>The matrix cannot be null/ empty., and at least has one element. R:
   *
   * <p>1. rows as X axis， and columns as Y axis. The 1 rectangle can be considered as the a series
   * of histograms based on current row
   *
   * <p>Thus the matrix can be converted to 4 histograms:
   *
   * <p>1st row: [1, 0 , 1, 0, 0]
   *
   * <p>2nd row: [2, 0 , 2, 1, 1]
   *
   * <p>3rd row: [3, 1 , 3, 2, 2]
   *
   * <p>4th row: [4, 0 , 0, 3, 0]
   *
   * <p>2. Apply the monolithic increasing stack to get the max area among the histogram
   * T: <p>[[1]], problem example
   */

  public static int maxRetangleArea(char[][] A) {
    int[][] H = new int[A.length][A[0].length];
    for (int row = 0; row < A.length; row++) {
      for (int col = 0; col < A[0].length; col++) {
        if (row == 0) {
          H[row][col] = (A[row][col] - '0');
        } else {
          H[row][col] = (A[row][col] - '0') == 0 ? 0 : (H[row - 1][col]) + 1;
        }
      }
    }
    int maxArea = 0;
    for (int[] heights : H) {
      maxArea = Math.max(maxArea, largestRectangleArea(heights));
    }
    return maxArea;
  }

  public static void main(String[] args) {
    char[][] test1 = new char[][] {{'1'}};
    char[][] test2 =
        new char[][] {
          {'1', '0', '1', '0', '0'},
          {'1', '0', '1', '1', '1'},
          {'1', '1', '1', '1', '1'},
          {'1', '0', '0', '1', '0'}
        };
    assertEquals(1, maxRetangleArea(test1));
    assertEquals(6, maxRetangleArea(test2));
  }
}
