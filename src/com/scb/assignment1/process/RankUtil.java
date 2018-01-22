/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scb.assignment1.process;

import com.scb.assignment1.model.CardModel;
import com.scb.assignment1.model.PlayerModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Kittipong.TH
 */
public class RankUtil {

    public static boolean checkStraightFlush(PlayerModel player) throws Exception {
        //Straight flush:​ 5 cards of the same suit with consecutive values. Ranked by the highest card in the hand.
        CardModel card = player.getCard();
        int[] cardValue = card.getCardValue();
        for (int i = 0; i < cardValue.length - 1; i++) {
            if (cardValue[i] != cardValue[i + 1] - 1) {
                return false;
            }
        }

        if (findNumCardSuit(card.getCardSuit(), card.getCardSuit()[0].code) == 5) {
            card.setRank(CardUtil.PokerRank.STRAIGHT_FLUSH);
            card.addRankCondition(cardValue[4]); // Highest card in the hand.
            return true;
        }
        return false;
    }

    public static boolean checkFourOfAKind(PlayerModel player) throws Exception {
        //Four of a kind:​ 4 cards with the same value. Ranked by the value of the 4 cards.
        CardModel card = player.getCard();
        int[] cardValue = card.getCardValue();
        if (findNumValueArr(cardValue, cardValue[1]) == 4) {
            card.setRank(CardUtil.PokerRank.FOUR_OF_A_KIND);
            card.addRankCondition(cardValue[1]); // 1st One of 4 cards with same value.
            for (int c : cardValue) {
                if (c != cardValue[1]) {
                    card.addRankCondition(c); // 2th Other card
                    break;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean checkFullHourse(PlayerModel player) throws Exception {
        //Full House:​ 3 cards of the same value, with the remaining 2 cards forming a pair. Ranked by the value of the 3 cards.
        CardModel card = player.getCard();
        int[] cardValue = card.getCardValue();
        // 2 Case
        // Case 1 : OO0XX
        if ((cardValue[0] == cardValue[1]) && (cardValue[1] == cardValue[2])
                && (cardValue[3] == cardValue[4])) {
            card.setRank(CardUtil.PokerRank.FULL_HOUSE);
            card.addRankCondition(cardValue[0]); // One of 3 cards with same value.
            card.addRankCondition(cardValue[3]); // One of 2 cards with same value.
            return true;
        }
        // Case 2: OOXXX
        if ((cardValue[0] == cardValue[1])
                && (cardValue[2] == cardValue[3]) && (cardValue[3] == cardValue[4])) {
            card.setRank(CardUtil.PokerRank.FULL_HOUSE);
            card.addRankCondition(cardValue[2]); // One of 3 cards with same value.
            card.addRankCondition(cardValue[0]); // One of 2 cards with same value.            
            return true;
        }
        return false;
    }

    public static boolean checkFlush(PlayerModel player) throws Exception {
        //Flush:​ Hand contains 5 cards of the same suit. Hands which are both flushes are ranked using the rules for High Card.
        CardUtil.CardSuit[] cardSuit = player.getCard().getCardSuit();
        if (findNumCardSuit(cardSuit, cardSuit[0].code) == 5) {
            player.getCard().setRank(CardUtil.PokerRank.FLUSH);
            player.getCard().addRankCondition(player.getCard().getCardValue()[0]); // Highest card in the hand.
            return true;
        }
        return false;
    }

    public static boolean checkStraight(PlayerModel player) throws Exception {
        //Straight:​ Hand contains 5 cards with consecutive values. Hands which both contain a straight are ranked by their highest card.
        CardModel card = player.getCard();
        int[] cardValue = card.getCardValue();
        for (int i = 0; i < cardValue.length - 1; i++) {
            if (cardValue[i] != cardValue[i + 1] - 1) {
                return false;
            }
        }
        card.setRank(CardUtil.PokerRank.STRAIGHT);
        card.addRankCondition(cardValue[4]); // Highest card in the hand.
        return true;
    }

    public static boolean checkThreeOfAKind(PlayerModel player) throws Exception {
        //Three of a Kind:​ Three of the cards in the hand have the same value. Hands which both contain three of a kind are ranked by the value of the 3 cards. 
        CardModel card = player.getCard();
        int[] cardValue = card.getCardValue();
        if (findNumValueArr(cardValue, cardValue[2]) == 3) {
            card.setRank(CardUtil.PokerRank.THREE_OF_A_KIND);
            cardValue = removeValueInArr(cardValue, cardValue[2]);
            Arrays.sort(cardValue);
            card.addRankCondition(card.getCardValue()[2]); // 1st One of 3 cards with same value.
            card.addRankCondition(cardValue[1]); // 2nd Highest of other card. 
            card.addRankCondition(cardValue[0]); //3rd Other card.   

            return true;
        }
        return false;
    }

    public static boolean checkTwoPairs(PlayerModel player) throws Exception {
        //Two Pairs:​ The hand contains 2 different pairs. Hands which both contain 2 pairs are ranked by the value of their highest pair. 
        //Hands with the same highest pair are ranked by the value of their other pair. If these values are the same the hands are ranked by the value of the remaining card. 

        // 3 case 
        CardModel card = player.getCard();
        int[] cardValue = card.getCardValue();
        boolean result = false;
        // case 1 : OOAXX
        if (cardValue[0] == cardValue[1] && cardValue[3] == cardValue[4]) {
            card.addRankCondition(card.getCardValue()[3]); // Highest pair.
            card.addRankCondition(card.getCardValue()[0]); // Other pair.
            card.addRankCondition(card.getCardValue()[2]); // Other card.
            result = true;
            // case 2: AOOXX    
        } else if (cardValue[1] == cardValue[2] && cardValue[3] == cardValue[4]) {
            card.addRankCondition(card.getCardValue()[3]); // Highest pair.
            card.addRankCondition(card.getCardValue()[1]); // Other pair.
            card.addRankCondition(card.getCardValue()[2]); // Other card.
            result = true;
            // case 3 : OOXXA    
        } else if (cardValue[0] == cardValue[1] && cardValue[2] == cardValue[3]) {
            card.addRankCondition(card.getCardValue()[2]); // Highest pair.
            card.addRankCondition(card.getCardValue()[0]); // Other pair.
            card.addRankCondition(card.getCardValue()[4]); // Other card.
            result = true;
        }
        if (result) {
            card.setRank(CardUtil.PokerRank.TWO_PAIRS);
        }
        return result;
    }

    public static boolean checkPair(PlayerModel player) throws Exception {
        //Pair:​ 2 of the 5 cards in the hand have the same value. Hands which both contain a pair are ranked by the value of the cards forming the pair. 
        //If these values are the same, the hands are ranked by the values of the cards not forming the pair, in decreasing order. 
        Set<Integer> temp = new HashSet<Integer>();
        int[] cardValue;
        for (int c : player.getCard().getCardValue()) {
            if (temp.contains(c)) {
                player.getCard().setRank(CardUtil.PokerRank.PAIR);
                player.getCard().addRankCondition(c); // Card value of pair.

                cardValue = removeValueInArr(player.getCard().getCardValue(), c); // Remove pair
                Arrays.sort(cardValue);
                for (int i = 0; i < cardValue.length; i++) {
                    player.getCard().addRankCondition(cardValue[i]);
                }
                return true;
            } else {
                temp.add(c);
            }
        }
        return false;
    }

    public static boolean checkHighCard(PlayerModel player) throws Exception {
        player.getCard().setRank(CardUtil.PokerRank.HIGH_CARD);
        for (int i : player.getCard().getCardValue()) {
            player.getCard().addRankCondition(i);
        }
        return true;
    }

    private static int findNumCardSuit(CardUtil.CardSuit[] arr, char value) {
        int num = 0;
        for (CardUtil.CardSuit a : arr) {
            if (a.code == value) {
                num++;
            }
        }
        return num;
    }

    private static int findNumValueArr(char[] arr, char value) {
        int num = 0;
        for (char a : arr) {
            if (a == value) {
                num++;
            }
        }
        return num;
    }

    private static int findNumValueArr(int[] arr, int value) {
        int num = 0;
        for (int a : arr) {
            if (a == value) {
                num++;
            }
        }
        return num;
    }

    private static int[] removeValueInArr(int[] source, int value) {
        List<Integer> temp = new ArrayList<>();
        for (int i : source) {
            if (i != value) {
                temp.add(i);
            }
        }
        return toIntArray(temp);
    }

    private static int[] toIntArray(List<Integer> list) {
        int[] r = new int[list.size()];
        int i = 0;
        for (Integer e : list) {
            r[i++] = e;
        }
        return r;
    }
}
