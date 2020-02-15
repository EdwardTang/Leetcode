package com.company.vmware.hangman;

public class HangmanTrieNode {
  char val;
  HangmanTrieNode[] next;
  String word;

  public HangmanTrieNode(char val) {
    this.val = val;
    this.next = new HangmanTrieNode[26];
  }
}
