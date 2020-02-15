package com.company.vmware.myrand;

import java.util.StringJoiner;

public final class MyRand {

  /**
   * Generate random decimal number between [5,55] inclusively with given rand_0() that randomly return 0 or 1;
   * @return
   */
  public static int getRand() {

    StringJoiner s = new StringJoiner("");
    while(true) {
      if(s.length() < 8) {
        s.add(String.valueOf(oneOrZero()));
        continue;
      }

      int v = Integer.parseInt(s.toString(), 2);

      if(v >= 5 && v<=55) {
        return v;
      } else {
        s = new StringJoiner("");
      }
    }
  }


  /**
   * rand_0() that randomly return 0 or 1
   * @return
   */
  private static int oneOrZero() {
      return (int)Math.round(Math.random());
  }

  public static void main(String[] args) {
    for(int i = 0; i < 10; i++) {
      System.out.println(MyRand.getRand());
    }
  }


}
