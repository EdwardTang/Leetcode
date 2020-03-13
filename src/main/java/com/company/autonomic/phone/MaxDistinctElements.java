package com.company.autonomic.phone;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class MaxDistinctElements {

  /**
   * Maximum distinct elements after removing k elements
   *
   * <p>Given an array arr[] containing n elements. The problem is to find maximum number of
   * distinct elements (non-repeating) after removing k elements from the array. Note: 1 <= k <= n.
   *
   * <p>Examples:
   *
   * <p>Input : arr[] = {5, 7, 5, 5, 1, 2, 2}, k = 3 Output : 4 Remove 2 occurrences of element 5
   * and 1 occurrence of element 2.
   *
   * <p>Input : arr[] = {1, 2, 3, 4, 5, 6, 7}, k = 5 Output : 2
   *
   * <p>Input : arr[] = {1, 2, 2, 2}, k = 1 Output : 1
   *
   * <p>Approach: Following are the steps:
   *
   * <p>Create a hash table to store the frequency of each element. Insert frequency of each element
   * in a max heap. Now, perform the following operation k times. Remove an element from the max
   * heap. Decrement its value by 1. After this if element is not equal to 0, then again push the
   * element in the max heap. After the completion of step 3, the number of elements in the max heap
   * is the required answer.
   *
   * <p>High level: Reducing the most frequent elements to get max distinct element number. Mid
   * -level: Insert the frequencies of each, do K times of reducing the frequency on the top of the
   * max heap.
   */
  public static int maxDistinctElementNumber(int[] input, int k) {
    Map<Integer, Integer> freqMap = new HashMap<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

    // Init the frequency map
    for (int num : input) {
      Integer freq = freqMap.get(num);
      if (freq == null) {
        freq = 0;
      }
      freq++;
      freqMap.put(num, freq);
    }

    for (Map.Entry<Integer, Integer> e : freqMap.entrySet()) {
      maxHeap.offer(e.getValue());
    }

    for (int i = k; i > 0; i--) {
      Integer freq = maxHeap.poll();
      freq--;
      if (freq > 0) {
        maxHeap.offer(freq);
      }
    }
    return maxHeap.size();
  }

  public static void main(String args[]) {
    int[] arr1 = {5, 7, 5, 5, 1, 2, 2};
    int k1 = 3;
    int[] arr2 = {1, 2, 3, 4, 5, 6, 7};
    int k2 = 5;
    int[] arr3 = {1, 2, 2, 2};
    int k3 = 1;
    System.out.println(
        "For arr1 and k1 Maximum distinct elements = " + maxDistinctElementNumber(arr1, k1));
    System.out.println(
        "For arr2 and k2 Maximum distinct elements = " + maxDistinctElementNumber(arr2, k2));
    System.out.println(
        "For arr3 and k3 Maximum distinct elements = " + maxDistinctElementNumber(arr3, k3));
  }
}
