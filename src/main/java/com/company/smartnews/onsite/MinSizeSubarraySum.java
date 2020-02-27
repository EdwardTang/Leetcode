package com.company.smartnews.onsite;

import static org.junit.Assert.assertEquals;

public class MinSizeSubarraySum {

  /**
   * C:
   * <p> What is the value range of the element in the array? Is negative number allow?</p>
   * <p> What is sum range of the array?</p>
   * <p></p>
   * A:
   * <p> We can assume that the sum range of the array is less than Integer.MAX_VALUE</p>
   * <p> The element is always positive in the array, all element is integer</p>
   * <p> If the input array is null, empty, or target not found, return -1</p>
   * R:
   * <p> 1. Use two pointer start and end initially points at the beginning of the array</p>
   * <p> 2.1 The semantics of start pointer: the left side of  start pointer is the element we have
   * processed and left behind</>
   * <p> 2.2 The semantics of end pointer: the left side (include the pointer) of end pointer is the
   * elements that we can keep in the subarray whose sum is >= target,
   * the right side fo the end pointer are the elements we are about to process</p>
   * <p> 3.1 When the sum of subarray between start and end is less than target, expand the subarray
   * by moving end pointer until we found the sum >= target</>
   * <p> 3.2 When we found the sum >= target, record and update the min size, update the sum by
   * reducing what start pointer points, move the start pointer to shorten the subarray, until the
   * sum is  < target </p>
   * <p> 3.3 keep doing 3.1 and 3.2 until end  == length of nums</p>
   * T:
   * <p>null, empty -> -1</p>
   * <p>[1,2], t = 7 -> -1</p>
   * <p>[2,1,3,2,7] t = 7 -> 1 </p>
   * <p>[2,1,3,2] t = 5 -> 2</p>
   */

  public static int minSize(int[] nums, int target) {
    if (nums == null || nums.length == 0 || target <= 0) {
      return -1;
    }
    int res = Integer.MAX_VALUE;
    int sum = 0;
    int start = 0;
    for (int end = 0; end < nums.length; end++) {
      sum += nums[end];
      while (sum >= target && start <= end) {
        res = Math.min(res, end - start + 1);
        sum -= nums[start++];
      }
    }
    return res == Integer.MAX_VALUE ? -1 : res;
  }

  public static void main(String[] args) {
    assertEquals(-1, minSize(null, 7));
    assertEquals(-1, minSize(new int[]{}, 7));
    assertEquals(-1, minSize(new int[]{1, 2}, 7));
    assertEquals(1, minSize(new int[]{2, 1, 3, 2, 7}, 7));
    assertEquals(2, minSize(new int[]{2, 1, 3, 2}, 5));

  }

}
