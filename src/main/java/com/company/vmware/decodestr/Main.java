package com.company.vmware.decodestr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
  public static List decode(String encoded) {
    List<String> ans = new ArrayList<>();

    StringBuilder input1 = new StringBuilder();

    // append a string into StringBuilder input1
    input1.append(encoded);

    // reverse StringBuilder input1
    input1 = input1.reverse();

    // print reversed String
    // System.out.println(input1);

    char[] encodedArr = encoded.toCharArray();

    decodeHelper(0, encodedArr.length, new StringBuilder(), ans, input1.toString());

    for (String s : ans) {
      System.out.println(s);
    }
    return ans;
  }

  public static void decodeHelper(
      int start, int length, StringBuilder sb, List<String> ans, String encoded) {

    if (start >= length - 1) {
      ans.add(sb.toString());
      return;
    }
    List<String> splitList = new ArrayList<>();
    if (start + 2 <= length) {
      // get the first 2 chars of the string
      splitList.add(encoded.substring(start, start + 2));
    }
    if (start + 3 <= length) {
      // get the first 3 chars of the string
      splitList.add(encoded.substring(start, start + 3));
    }
    // For each substring in splitList, check if it is valid ASCII value,
    for (String each : splitList) {
      if (isValid(each)) {
        // System.out.println(each);
        sb.append((char) (Integer.parseInt(each)));
        decodeHelper(start + each.length(), length, sb, ans, encoded);
        sb.setLength(sb.length() - 1); // back tracking
      }
    }
  }

  public static boolean isValid(String s) {
    int val = Integer.parseInt(s);
    return (val >= 10 && val <= 126);
  }

  public static void main(String[] args) {
    System.out.println("Hello World!");
    // String ss = "0018014111117811180180110127";
    // decode(ss);
    // decode("64101301011794019923111611236112117900179231116112312161150180150189792310140161123511501231019901110130150180180110161101137");
    decode("101411797877682311117");
  }
}
