package com.company.google.topmedium.maxsquare.similar;


import static org.junit.Assert.assertEquals;

import java.util.Arrays;

class _764LargestPlusSign {

  /**
   * In a 2D grid from (0, 0) to (N-1, N-1), every cell contains a 1, except those cells in the
   * given list mines which are 0. What is the largest axis-aligned plus sign of 1s contained in the
   * grid? Return the order of the plus sign. If there is none, return 0.
   *
   * <p>An "axis-aligned plus sign of 1s of order k" has some center grid[x][y] = 1 along with 4
   * arms of length k-1 going up, down, left, and right, and made of 1s. This is demonstrated in the
   * diagrams below. Note that there could be 0s or 1s beyond the arms of the plus sign, only the
   * relevant area of the plus sign is checked for 1s.
   *
   * <p>Input: N = 5, mines = [[4, 2]] Output: 2 Explanation: 11111 11111 11111 11111 11011 In the
   * above grid, the largest plus sign can only be order 2.
   */
  public static int orderOfLargestPlusSign(int N, int[][] mines) {
    int[][] dp = new int[N][N];

    for (int[] row : dp) {
      Arrays.fill(row, N);
    }

    for (int[] m : mines) {
      dp[m[0]][m[1]] = 0;
    }

    for (int i = 0; i < N; i++) {

      int left = 0;
      for (int j = 0; j < N; j++) {
        left = dp[i][j] == 0 ? 0 : left + 1;
        dp[i][j] = Math.min(dp[i][j], left);
      }

      int right = 0;
      for (int j = N - 1; j >= 0; j--) {
        right = dp[i][j] == 0 ? 0 : right + 1;
        dp[i][j] = Math.min(dp[i][j], right);
      }

      int top = 0;
      for (int j = 0; j < N; j++) {
        top = dp[j][i] == 0 ? 0 : top + 1;
        dp[j][i] = Math.min(dp[j][i], top);
      }

      int bottom = 0;
      for (int j = N - 1; j >= 0; j--) {
        bottom = dp[j][i] == 0 ? 0 : bottom + 1;
        dp[j][i] = Math.min(dp[j][i], bottom);
      }
    }

    int res = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        res = Math.max(res, dp[i][j]);
      }
    }
    return res;
  }

  public static void main(String[] args) {
    assertEquals(2, orderOfLargestPlusSign(5, new int[][] {{4, 2}}));
  }
}