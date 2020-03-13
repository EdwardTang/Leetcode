package com.laioffer.dfs;

import java.util.ArrayList;
import java.util.List;

public class _99Cents {

  /**
   * Given a number of different denominations of coins (e.g., 1 cent, 5 cents, 10 cents, 25 cents),
   * get all the possible ways to pay a target number of cents.
   *
   * <p>Arguments
   *
   * <p>coins - an array of positive integers representing the different denominations of coins,
   * there are no duplicate numbers and the numbers are sorted by descending order, eg. {25, 10, 5,
   * 2, 1} target - a non-negative integer representing the target number of cents, eg. 99
   * Assumptions
   *
   * <p>coins is not null and is not empty, all the numbers in coins are positive target >= 0 You
   * have infinite number of coins for each of the denominations, you can pick any number of the
   * coins. Return
   *
   * <p>a list of ways of combinations of coins to sum up to be target. each way of combinations is
   * represented by list of integer, the number at each index means the number of coins used for the
   * denomination at corresponding index. Examples
   *
   * <p>coins = {2, 1}, target = 4, the return should be
   *
   * <p>[
   *
   * <p>[0, 4], (4 cents can be conducted by 0 * 2 cents + 4 * 1 cents)
   *
   * <p>[1, 2], (4 cents can be conducted by 1 * 2 cents + 2 * 1 cents)
   *
   * <p>[2, 0] (4 cents can be conducted by 2 * 2 cents + 0 * 1 cents)
   *
   * <p>]
   */
  public static void main(String[] args) {
    int[] coin = new int[] {25, 10, 5, 1};
    findCoinCombination(coin, 99, 0, new int[4]);
    List<List<Integer>> result = combinations(99, coin);
  }

  public static List<List<Integer>> combinations(int target, int[] coins) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    List<Integer> cur = new ArrayList<Integer>();
    helper(target, coins, 0, cur, result);
    return result;
  }

  private static void helper(
      int target, int[] coins, int index, List<Integer> cur, List<List<Integer>> result) {
    if (index == coins.length - 1) {
      if (target % coins[coins.length - 1] == 0) {
        cur.add(target / coins[coins.length - 1]);
        result.add(new ArrayList<Integer>(cur));
        cur.remove(cur.size() - 1);
      }
      return;
    }
    int max = target / coins[index];
    for (int i = 0; i <= max; i++) {
      cur.add(i);
      helper(target - i * coins[index], coins, index + 1, cur, result);
      cur.remove(cur.size() - 1);
    }
  }

  public static void findCoinCombination(int[] coin, int moneyLeft, int index, int[] sol) {
    if (index == 3) {
      sol[index] = moneyLeft;
      printSol(sol, coin);
      return;
    }

    for (int i = 0; i <= moneyLeft / coin[index]; i++) {
      sol[index] = i;
      findCoinCombination(coin, moneyLeft - i * coin[index], index + 1, sol);
    }
  }

  private static void printSol(int[] sol, int[] coin) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < sol.length; i++) {
      sb.append(sol[i]);
      sb.append('x');
      sb.append(coin[i]);
      sb.append(" cent ");
    }
    System.out.println(sb.toString());
  }
}
