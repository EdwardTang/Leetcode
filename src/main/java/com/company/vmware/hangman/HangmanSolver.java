package com.company.vmware.hangman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Design data structure for HangMan solver game. We are given array of lexocographically sorted
 * strings 'dict[]' as input and a pattern 'puzzle', return list of all the words matching pattern
 * 'puzzle'. 'puzzle' is a pattern containing underscore character "_".
 *
 * <p>class HangmanSolver {
 *
 * <p>public HangmanSolver(String[] dict) { // todo }
 *
 * <p>public List<String> solve(String puzzle) { // todo } } Here solve method could be called
 * multiple times however HangManSolver() will be called only once. So the problem is to process
 * strings in the input array and store in a data structure efficient for searching strings matching
 * given pattern.
 *
 * <p>Example:
 *
 * <p>HangmanSolver solver = new HangmanSolver(["ant", "feet", "meet", "zebra"]);
 * solver.solve("_e_t"); // ["feet", "meet"]
 */
public class HangmanSolver {

  private HangmanTrieNode root;

  public HangmanSolver(List<String> dict) {
    root = new HangmanTrieNode(' ');
    for (String word : dict) {
      HangmanTrieNode ref = root;
      for (char c : word.toCharArray()) {
        ref.next[c - 'a'] = new HangmanTrieNode(c);
        ref = ref.next[c - 'a'];
      }
      ref.word = word;
    }
  }

  public List<String> solve(String puzzle) {
    List<String> result = new ArrayList<>();
    dfs(root, result, puzzle, 0);
    return result;
  }

  private void dfs(HangmanTrieNode root, List<String> result, String puzzle, int cur) {
    // base case: no such char root in the dictionary
    if (root == null || cur > puzzle.length()) {
      return;
    }
    if (cur == puzzle.length()) {
      if (root.word != null) {
        result.add(root.word);
      }
      return;
    }

    char pChar = puzzle.charAt(cur);
    if (pChar == '_') {
      for (HangmanTrieNode next : root.next) {
        dfs(next, result, puzzle, cur + 1);
      }
    } else {
      dfs(root.next[pChar - 'a'], result, puzzle, cur + 1);
    }
  }

  public static void main(String[] args) {
    List<String> dict1 = Arrays.asList(new String[] {"ant", "feet", "meet", "zebra"});
    HangmanSolver solver1 = new HangmanSolver(dict1);
    System.out.println(solver1.solve("_e_t").toString());
  }
}
