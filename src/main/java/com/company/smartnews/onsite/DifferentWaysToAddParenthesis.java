package com.company.smartnews.onsite;

import java.util.ArrayList;
import java.util.List;

public class DifferentWaysToAddParenthesis {

  public static void main(String[] args) {
    String test1 = "2-1-1";
    System.out.println(findWays(test1).toString());
    String test2 = "2*3-4*5";
    System.out.println(findWays(test2).toString());
  }

  public static List<Integer> findWays(String input) {
    String[] nums = input.split("[*,+,-]");
    char[] operators = input.replaceAll("[0-9]", "").toCharArray();
    List[][] mem = new List[nums.length][nums.length];
    return dfs(nums, operators, 0, nums.length - 1, mem);
  }

  private static List<Integer> dfs(
      String[] nums, char[] operators, int start, int end, List[][] mem) {
    if (mem[start][end] != null) {
      return mem[start][end];
    }

    if (start == end) {
      List<Integer> baseCase = new ArrayList<>();
      baseCase.add(Integer.parseInt(nums[start]));
      mem[start][end] = baseCase;
      return baseCase;
    }

    List<Integer> result = new ArrayList<>();
    for (int k = start; k < end; k++) {
      List<Integer> group1 = dfs(nums, operators, start, k, mem);
      List<Integer> group2 = dfs(nums, operators, k + 1, end, mem);
      for (Integer num1 : group1) {
        for (Integer num2 : group2) {
          result.add(calcuate(operators[k], num1, num2));
        }
      }
    }
    mem[start][end] = result;
    return result;
  }

  private static Integer calcuate(char operator, Integer nums1, Integer nums2) {
    switch (operator) {
      case '*':
        return nums1 * nums2;
      case '+':
        return nums1 + nums2;
      default:
        return nums1 - nums2;
    }
  }
}
