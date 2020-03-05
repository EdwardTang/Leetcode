package com.company.yoyo.onsite;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MajorityElementVariant {

  /**
   * Leetcode 229. Majority Element II Variant Given an sorted integer array of size n, find all
   * elements that appear more than ⌊ n/3 ⌋ times.
   *
   * <p>Note: The algorithm should run in linear time and in O(1) space.
   *
   * <p>Example 1:
   *
   * <p>Input: [1, 3, 3] Output: [3]
   *
   * <p>Example 2:
   *
   * <p>Input: [1, 2, 2, 3, 6, 6, 6, 7] Output: [6]
   */
  public static void main(String[] args) {
    int[] test1 = new int[] {1, 2, 2, 3, 6, 6, 6, 7};
    int[] test2 = new int[] {1};
    int[] test3 = new int[] {1, 2};
    int[] test4 = new int[] {1, 2, 3};
    // There are more than 33 integer '30's in the test 5 array, the length of test 5 is 100
    int[] test5 =
        new int[] {
          2, 3, 4, 4, 5, 5, 9, 11, 11, 14, 14, 15, 18, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30,
          30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30,
          30, 30, 56, 57, 58, 62, 62, 62, 63, 65, 67, 67, 68, 70, 71, 71, 71, 71, 72, 72, 74, 75,
          75, 76, 76, 78, 78, 79, 79, 79, 79, 81, 82, 83, 83, 84, 84, 86, 87, 88, 90, 91, 92, 94,
          94, 95, 95, 97, 97, 98, 99, 99, 100, 100
        };
    List<int[]> tests = new ArrayList<>();
    tests.add(test1);
    tests.add(test2);
    tests.add(test3);
    tests.add(test4);
    tests.add(test5);

    int i = 1;
    for (int[] testCase : tests) {
      List<Integer> myResult = myMajorityElement(testCase);
      List<Integer> expected = majorityElement(testCase);
      Collections.sort(expected);
      System.out.println("test " + i + " My Result: " + myResult.toString());
      System.out.println("test " + i + " Expected: " + expected.toString());
      i++;
      assertEquals(expected, myResult);
    }
  }

  public static List<Integer> majorityElement(int[] nums) {
    List<Integer> result = new ArrayList<>();
    if (nums == null || nums.length == 0) {
      return result;
    }
    // At maximum we can have two elments qualified the condition
    int count1 = 0;
    int count2 = 0;
    int candidate1 = 0;
    int candidate2 = 1;
    for (int num : nums) {
      //      System.out.println(candidate1 + " : " + count1);
      //      System.out.println(candidate2 + " : " + count2);
      if (num == candidate1) {
        count1++;
      } else if (num == candidate2) {
        count2++;
      } else if (count1 == 0) {
        candidate1 = num;
        count1 = 1;
      } else if (count2 == 0) {
        candidate2 = num;
        count2 = 1;
      } else {
        count1--;
        count2--;
      }
    }
    //    System.out.println(candidate1 + " : " + count1);
    //    System.out.println(candidate2 + " : " + count2);
    count1 = 0;
    count2 = 0;
    for (int num : nums) {
      if (num == candidate1) {
        count1++;
      } else if (num == candidate2) {
        count2++;
      }
    }
    if (count1 > nums.length / 3) {
      result.add(candidate1);
    }
    if (count2 > nums.length / 3) {
      result.add(candidate2);
    }
    return result;
  }

  public static List<Integer> myMajorityElement(int[] nums) {
    Set<Integer> res = new HashSet<>();

    if (nums == null || nums.length == 0) {
      return new ArrayList<>();
    } else if (nums.length < 3) {
      List<Integer> shortRes = new ArrayList<>(2);
      for (int num : nums) {
        shortRes.add(num);
      }
      return shortRes;
    }

    //    Arrays.sort(nums);
    int len = nums.length;
    for (int pivot = len / 3; pivot < len; pivot += len / 3) {
      int target = nums[pivot];
      int left = pivot - len / 3;
      int first = findFirst(target, nums, left, pivot);
      int right = (pivot + len / 3 > len - 1) ? len - 1 : pivot + len / 3;
      int last = findLast(target, nums, pivot, right);
      if (last - first + 1 > len / 3) {
        res.add(target);
      }
    }
    return new ArrayList<>(res);
  }

  private static int findLast(int T, int[] nums, int pivot, int right) {
    int l = pivot;
    int r = right;
    while (l + 1 < r) {
      int mid = l + (r - l) / 2;
      if (nums[mid] == T) {
        l = mid;
      } else if (nums[mid] > T) {
        r = mid;
      }
    }
    if (nums[r] == T) {
      return r;
    } else {
      return l;
    }
  }

  private static int findFirst(int T, int[] nums, int left, int pivot) {
    int l = left;
    int r = pivot;
    while (l + 1 < r) {
      int mid = l + (r - l) / 2;
      if (nums[mid] == T) {
        r = mid;
      } else if (nums[mid] < T) {
        l = mid;
      }
    }
    if (nums[l] == T) {
      return l;
    } else {
      return r;
    }
  }
}
