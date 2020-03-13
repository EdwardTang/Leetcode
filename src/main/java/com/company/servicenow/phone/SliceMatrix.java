package com.company.servicenow.phone;

import java.util.HashSet;
import java.util.Set;

public class SliceMatrix {

  /**
   * Write a function to slice the matrix with given row numbers and given columns numbers
   */
  public static void main(String[] args) {
    int[][] test =
        new int[][] {
          new int[] {1, 1, 1, 1},
          new int[] {0, 0, 0, 0},
          new int[] {2, 1, 3, 3},
          new int[] {1, 2, 4, 4}
        };
    int[] rows = new int[] {0, 1};
    int[] cols = new int[] {1, 2};
    // rows
    // 0    [1 1 1 1
    // 1     0 0 0 0
    //       2 1 3 3    ==>  [2 3
    //       1 2 4 4]         1 4]
    // cols    1 2
    int[][] result = findSlicedMatrix(test, rows, cols);
    for (int[] r : result) {
      for (int num : r) {
        System.out.print(num + " ");
      }
      System.out.println();
    }
  }

  /**
   * Assumption: M, rows, cols are not null nor empty.
   * High level: use set to check if current row or column should be skipped. Use pointer to create new result 2D-array
   * Time: (N*M), Space(rows.length + cols.length)
   */
  public static int[][] findSlicedMatrix(int[][] M, int[] rows, int[] cols) {
    // todo: validate input
    int totalRows = M.length;
    int totalCols = M[0].length;
    int remainRows = totalRows - rows.length;
    int remainCols = totalCols - cols.length;
    int[][] res = new int[remainRows][remainCols];

    // find the remaining row index and col index in rows and cols,
    Set<Integer> slicedRows = new HashSet<>();
    Set<Integer> slicedCols = new HashSet<>();

    for (int row : rows) {
      slicedRows.add(row);
    }
    for (int col : cols) {
      slicedCols.add(col);
    }

    for (int i = 0, curRow = 0; i < totalRows; i++) {
      if (!slicedRows.contains(i)) {
        for (int j = 0, curCol = 0; j < totalCols; j++) {
          if (!slicedCols.contains(j)) {
            if (curRow < remainRows && curCol < remainCols) {
              res[curRow][curCol] = M[i][j];
            }
            curCol++;
          }
        }
        curRow++;
      }
    }
    return res;
  }
}
