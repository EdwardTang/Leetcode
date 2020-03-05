package com.company.smartnews.onsite;

import static org.junit.Assert.assertArrayEquals;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * LaiCode 133. Merge K Sorted Array Medium Merge K sorted array into one big sorted array in
 * ascending order.
 *
 * <p>Assumptions
 *
 * <p>The input arrayOfArrays is not null, none of the arrays is null either.
 */
public class MergedKSortedArrays {

  public static void main(String[] args) {
    MergedKSortedArrays sol = new MergedKSortedArrays();

    int[][] test1 = new int[][]{{}, {1, 5, 7}, {4}, {2, 3, 5, 11}, {2, 4, 4, 6, 8}};
    int[] expected1 = new int[]{1, 2, 2, 3, 4, 4, 4, 5, 5, 6, 7, 8, 11};
    assertArrayEquals(sol.merge(test1), expected1);

    int[][] test2 = new int[][]{{1, 1, 1}, {2, 2, 2}, {3, 3, 3}, {1, 2, 3}};
    int[] expected2 = new int[]{1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3};
    assertArrayEquals(sol.merge(test2), expected2);
  }

  public int[] merge(int[][] arrayOfArrays) {
    // Write your solution here
    // Assumptions: arrayOfArrays is not
    PriorityQueue<Entry> minHeap = new PriorityQueue<Entry>(11, new MyComparator());
    int length = 0;
    for (int i = 0; i < arrayOfArrays.length; i++) {
      int[] array = arrayOfArrays[i];
      length += array.length;
      if (array.length != 0) {
        // We use two index to record the position of each element:
        // the index of the array in the arrayOfArrays,
        // the index of the element in the array.
        minHeap.offer(new Entry(i, 0, array[0]));
      }
    }
    int[] result = new int[length];
    int cur = 0;
    while (!minHeap.isEmpty()) {
      Entry tmp = minHeap.poll();
      result[cur++] = tmp.value;
      if (tmp.y + 1 < arrayOfArrays[tmp.x].length) {
        // reuse the same entry object but advance the index by 1.
        tmp.y++;
        tmp.value = arrayOfArrays[tmp.x][tmp.y];
        minHeap.offer(tmp);
      }
    }
    return result;
  }

  static class MyComparator implements Comparator<Entry> {

    @Override
    public int compare(Entry e1, Entry e2) {
      if (e1.value == e2.value) {
        return 0;
      }
      return e1.value < e2.value ? -1 : 1;
    }
  }

  static class Entry {

    // the row number
    int x;
    // the column number
    int y;
    // the corresponding value
    int value;

    Entry(int x, int y, int value) {
      this.x = x;
      this.y = y;
      this.value = value;
    }
  }
}
