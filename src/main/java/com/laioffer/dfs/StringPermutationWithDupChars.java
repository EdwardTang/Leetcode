package com.laioffer.dfs;

import java.util.ArrayList;
import java.util.List;

/*
Assumption:  (1) given input is char array ahd not null, not empty (2) output is a list of all permutation Strings
High level:  use DFS to traverse  all permutations. (1) N levels of recursion tree, each level represents one position. (2) at each level, we need traverse each node with remaining unused letters: i-th level, each node: (n - i) branches
Recursion tree:
																															root = aab
																			/                  |                             \
		i = 0, 1, 2              swap(0,0).                 swap(0,1)                 swap(0, 2)
position 0                  **a**ab                    ~~**a**ab~~                **b**aa                    n = 3
													/          \                /         \                /        \
		i = 1, 2          swap(1,1)   swap(1,2)        swap(1,1)     swap(1,2)
position 1            a**a**b     a**b**a        ~~a**a**b       a**b**a~~    b**a**a.   ~~b**a**a~~           * (3-1)
											 |            |              |                  |           |                |
    i = 2	           swap(2,2)   swap(2,2)
 position 2          aa**b**     ab**a**         ~~aa**b**          ab**a**~~     ba**a**    ~~ba**a**~~       * (3 - 2)
                                                                        clone each string at position 2: O(n)  *  O(n!) brances
Time: O(N!*N),    Space(the height of the recursion tree) = O(N)
*/
public class StringPermutationWithDupChars {
  public static List<String> findPermutations(char[] input) {
    List<String> result = new ArrayList<>();
    dfs(input, 0, result);
    return result;
  }

  public static void dfs(char[] input, int index, List<String> result) {

  }
}

