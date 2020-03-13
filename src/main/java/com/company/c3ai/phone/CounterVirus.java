package com.company.c3ai.phone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CounterVirus {

  public static void main(String[] args) {
    int[] tcpPacket = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    System.out.println(Arrays.toString(tcpPacket));
    iAmVirus(tcpPacket);
    System.out.println(Arrays.toString(tcpPacket));
    findComplementIndex(tcpPacket).forEach(i -> System.out.print(i + " "));
    System.out.println();
    changeMeBack(tcpPacket);
    System.out.println(Arrays.toString(tcpPacket));
  }

  private static void iAmVirus(int[] tcpPacket) {
    int n = tcpPacket.length;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if ((i + 1) * (j + 1) <= n) {
          tcpPacket[(i + 1) * (j + 1) - 1] = complement(tcpPacket[(i + 1) * (j + 1) - 1]);
          //          System.out.print(Arrays.toString(tcpPacket));
          //          System.out.print(" ");
          //          System.out.println((i + 1) * (j + 1));
        } else {
          break;
        }
      }
    }
  }

  private static int complement(int i) {
    return i == 0 ? 1 : 0;
  }

  private static List<Integer> findComplementIndex(int[] tcpPacket) {
    List<Integer> res = new ArrayList<>();
    for (int i = 0; i < tcpPacket.length; i++) {
      if (tcpPacket[i] == 1) {
        res.add(i);
      }
    }
    return res;
  }

  /**
   * Complement the digit at 0,3,8,15,24.....
   * @param tcpPacket given tcp packet
   */
  private static void changeMeBack(int[] tcpPacket) {
    // write your code here
    int index = 0;
    int i = 1;
    while (index < tcpPacket.length) {
      tcpPacket[index] = complement(tcpPacket[index]);
      index += i * 2 + 1;
      i++;
    }
  }
}
