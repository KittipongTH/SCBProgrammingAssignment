/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scb.assignment1.process;

import com.scb.assignment1.model.CardModel;
import java.util.Arrays;

/**
 *
 * @author Kittipong.TH
 */
public class CardUtil {

    public enum PokerRank {
        STRAIGHT_FLUSH("Straight flush", 9),
        FOUR_OF_A_KIND("Four of a kind", 8),
        FULL_HOUSE("Full House", 7),
        FLUSH("Flush", 6),
        STRAIGHT("Straight", 5),
        THREE_OF_A_KIND("Three of a Kind", 4),
        TWO_PAIRS("Two Pairs", 3),
        PAIR("Pair", 2),
        HIGH_CARD("High Card", 1);

        public final String name;
        public final int value;

        private PokerRank(String name, int value) {
            this.name = name;
            this.value = value;
        }
    }

    public enum CardSuit {
        CLUBS('C', "Clubs"),
        DIAMONDS('D', "Diamonds"),
        HEARTS('H', "HEARTS"),
        SPADES('S', "Spades");

        public final char code;
        public final String name;

        private CardSuit(char code, String name) {
            this.code = code;
            this.name = name;
        }
    }

    public static CardModel getCardData(String txt) throws Exception {
        try {
            CardModel card = new CardModel();
            card.setCardFull(txt.trim().split("\\ "));
            int[] cardValue = new int[card.getCardFull().length];
            CardSuit[] cardSuit = new CardSuit[card.getCardFull().length];
            for (int i = 0; i < card.getCardFull().length; i++) {
                cardValue[i] = convertCardValue(card.getCardFull()[i].charAt(0));
                cardSuit[i] = convertSuitEnum(card.getCardFull()[i].charAt(1));
            }
            // Sort suit and value
            Arrays.sort(cardValue);
            Arrays.sort(cardSuit);
            
            card.setCardValue(cardValue);
            card.setCardSuit(cardSuit);
            
            return card;
        } catch (Exception ex) {
            System.err.println("Problem to get card");
            throw ex;
        }
    }

    private static int convertCardValue(char c) {
        switch (c) {
            case 'T':
                return 10;
            case 'J':
                return 11;
            case 'Q':
                return 12;
            case 'K':
                return 13;
            case 'A':
                return 14;
            default:
                return Character.getNumericValue(c);
        }
    }

    private static String convertRank(int rank) {
        switch (rank) {
            case 9:
                return "Straight flush";
            case 8:
                return "Four of a kind";
            case 7:
                return "Full House";
            case 6:
                return "Flush";
            case 5:
                return "Straight";
            case 4:
                return "Three of a Kind";
            case 3:
                return "Two Pairs";
            case 2:
                return "Pair";
            default:
                return "High Card";
        }
    }

    private static CardSuit convertSuitEnum(char suit) {
        switch (suit) {
            case 'C':
                return CardSuit.CLUBS;
            case 'D':
                return CardSuit.DIAMONDS;
            case 'H':
                return CardSuit.HEARTS;
            case 'S':
                return CardSuit.SPADES;
            default:
                return null;
        }
    }    
    
    private static String convertSuit(char suit) {
        switch (suit) {
            case 'C':
                return "Clubs";
            case 'D':
                return "Diamonds";
            case 'H':
                return "Hearts";
            case 'S':
                return "Spades";
            default:
                return "";
        }
    }

    public static String convertCard(int card) {
        //  jack, queen, king, ace 
        switch (card) {
            //case 10 : return "T";
            case 11:
                return "Jack";
            case 12:
                return "Queen";
            case 13:
                return "King";
            case 14:
                return "Ace";
            default:
                return String.valueOf(card);
        }
    }
}
