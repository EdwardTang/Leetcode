package com.company.huluobo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FindBestPokerHand {

  private static final Map<Character, Integer> cardRankMap = new HashMap<>();
  static {
    cardRankMap.put('2', 2);
    cardRankMap.put('3', 3);
    cardRankMap.put('4', 4);
    cardRankMap.put('5', 5);
    cardRankMap.put('6', 6);
    cardRankMap.put('7', 7);
    cardRankMap.put('8', 8);
    cardRankMap.put('9', 9);
    cardRankMap.put('T', 10);
    cardRankMap.put('J', 11);
    cardRankMap.put('Q', 12);
    cardRankMap.put('K', 13);
    cardRankMap.put('A', 14);
  }

  enum HandName{
    STRAIGHT_FLUSH(9),
    FOUR_OF_A_KIND(8),
    FULL_HOUSE(7),
    FLUSH(6),
    STRAIGHT(5),
    THREE_OF_A_KIND(4),
    TWO_PAIRS(3),
    ONE_PAIR(2),
    HIGHEST_CARD(1);

    private int rank;

    public int getRank() {
      return rank;
    }

    private HandName(int rank) {
      this.rank = rank;
    }

  }

  public static void main(String[] args) {
    String cardsStr = "2C 3D 4S 5D 7H KD QH 6C JH 2D";
    int count = 0;
    HandName bestHand = HandName.HIGHEST_CARD;
//    for(List<String> hand : findHandCombination(cardsStr)) {
//
//      System.out.println("Hand " + count + " : ");
//      for(String card : hand) {
//        System.out.print(card);
//        System.out.print(" ");
//      }
//      HandName curHand = checkHandRank(hand);
//      if(bestHand.getRank() < curHand.getRank()) {
//        bestHand = curHand;
//        System.out.print(" best hand change to: " + bestHand.toString());
//      }
//      System.out.println();
//      count++;
//    }

    System.out.println("Best hand is: " + play(cardsStr).toString());
  }

  public static HandName play(String cardStr) {
    HandName bestHandName = HandName.HIGHEST_CARD;

    for(List<String> curHand : findHandCombination(cardStr)) {
      HandName curHandName = checkHandRank(curHand);
      if(curHandName.rank > bestHandName.rank) {
        bestHandName = curHandName;
      }
    }
    return bestHandName;
  }

  private static HandName checkHandRank(List<String> curHand) {
    if(isStraightFlush(curHand)) {
      return HandName.STRAIGHT_FLUSH;
    }

    if(isFourOfAKind(curHand)) {
      return HandName.FOUR_OF_A_KIND;
    }
    // FULL_HOUSE(7),
    if(isFullHouse(curHand)) {
      return HandName.FULL_HOUSE;
    }
//        FLUSH(6)
    if(isFlush(curHand)) {
      return HandName.FLUSH;
    }
//        STRAIGHT(5),
    if(isStraight(curHand)) {
      return HandName.STRAIGHT;
    }

//        THREE_OF_A_KIND(4),
    if(isThreeOfAKind(curHand)) {
      return HandName.THREE_OF_A_KIND;
    }
//        TWO_PAIRS(3),
    if(isTwoPairs(curHand)) {
      return HandName.TWO_PAIRS;
    }

//     ONE_PAIR(2),
    if(isOnePair(curHand)) {
      return HandName.ONE_PAIR;
    }
//        HIGHEST_CARD(1);

    return HandName.HIGHEST_CARD;


  }

  private static boolean isStraightFlush(List<String> curHand) {
    return isStraight(curHand) && isFlush(curHand);
  }

  private static boolean isFourOfAKind(List<String> curHand) {
    Set<Character> kindSet = new HashSet<>();
    int count = 0;
    for(String card : curHand) {
     if(kindSet.contains(card.charAt(0))) {
       count++;
     } else {
       kindSet.add(card.charAt(0));
     }
    }
    return count == 3;
  }

  private static boolean isFullHouse(List<String> curHand) {
    Map<Character, Integer> cardMap = new HashMap<>();
    for(String card : curHand) {
      char ch = card.charAt(0);
      Integer freq = cardMap.get(ch);
      if(freq == null) {
        cardMap.put(ch, 1);
      } else {
        cardMap.put(ch, freq + 1);
      }
    }
    return cardMap.containsValue(3) && cardMap.containsValue(2);
  }

  private static boolean isFlush(List<String> curHand) {
    Set<Character> suite = new HashSet<>();
    for(String card : curHand) {
      suite.add(card.charAt(1));
    }
    return suite.size() == 1;
  }

  private static boolean isStraight(List<String> curHand) {
    Map<Character, Integer> freqMap = new HashMap<>();
    int maxRank = 0;
    int minRank = 15;
    List aceToFive = Arrays.asList(new char[]{'A','2', '3', '4', '5'});
    for(String card : curHand) {
      char ch = card.charAt(0);
      if(maxRank < cardRankMap.get(ch)) {
        maxRank = cardRankMap.get(ch);
      }
      if(minRank > cardRankMap.get(ch)) {
        minRank = cardRankMap.get(ch);
      }
      Integer freq = freqMap.get(ch);
      if(freq == null) {
        freqMap.put(ch, 1);
      } else {
        freqMap.put(ch, freq + 1);
      }
    }
    Set<Integer> rankFreqSet = new HashSet<>(freqMap.values());
    if(rankFreqSet.size() == 1 && (maxRank - minRank) == 4 ) {
      return true;
    } else if(freqMap.keySet().containsAll(aceToFive)) {
      return true;
    } else {
      return false;
    }
  }

  private static boolean isThreeOfAKind(List<String> curHand) {
    Set<Character> kindSet = new HashSet<>();
    int count = 0;
    for(String card : curHand) {
      if(kindSet.contains(card.charAt(0))) {
        count++;
      } else {
        kindSet.add(card.charAt(0));
      }
    }
    return count == 2;
  }

  private static boolean isTwoPairs(List<String> curHand) {
    Map<Character, Integer> cardMap = new HashMap<>();
    for(String card : curHand) {
      char ch = card.charAt(0);
      Integer freq = cardMap.get(ch);
      if(freq == null) {
        cardMap.put(ch, 1);
      } else {
        cardMap.put(ch, freq + 1);
      }
    }
    return cardMap.values().contains(2) && cardMap.values().contains(1);
  }

  private static boolean isOnePair(List<String> curHand) {
    Map<Character, Integer> cardMap = new HashMap<>();
    for(String card : curHand) {
      char ch = card.charAt(0);
      Integer freq = cardMap.get(ch);
      if(freq == null) {
        cardMap.put(ch, 1);
      } else {
        cardMap.put(ch, freq + 1);
      }
    }
    return cardMap.values().contains(2);
  }


  public static List<List<String>> findHandCombination(String cardStr) {
    List<List<String>> possibleHands = new ArrayList<>();
    if(cardStr == null) {
      return possibleHands;
    }
    String[] cards = cardStr.split(" ");

    dfsHelper(cards, 5, new ArrayList<>(), 0, possibleHands);

    return possibleHands;
  }

  /**
   * Time Complexity: O((2^10) * 5)
   * @param cards
   * @param k
   * @param hand
   * @param index
   * @param possibleHands
   */
  private static void dfsHelper(String[] cards, int k, ArrayList<String> hand, int index, List<List<String>> possibleHands) {
    // terminate condition: when we have picked k cards
    if(hand.size() == k) {
      possibleHands.add(new ArrayList<>(hand));
      return;
    }
    // when we finishes determing for all the cards pick or not, we must return
    if(index == cards.length) {
      return;
    }
    // 1. not pick the card at index
    dfsHelper(cards,k, hand, index + 1, possibleHands);

    // 2. pick the card at index
    hand.add(cards[index]);
    dfsHelper(cards,k, hand, index + 1, possibleHands);
    hand.remove(hand.size() - 1);
  }
}
