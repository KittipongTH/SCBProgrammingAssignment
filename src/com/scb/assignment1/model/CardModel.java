/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scb.assignment1.model;

import com.scb.assignment1.process.CardUtil.CardSuit;
import com.scb.assignment1.process.CardUtil.PokerRank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Kittipong.TH
 */
public class CardModel {
    
    private String[] cardFull; // Keep card value and suit
    private int[] cardValue; // Only card value
    private CardSuit[] cardSuit; // Only card suit

    private PokerRank rank = PokerRank.HIGH_CARD; // Poker rank in the hand. (Default High card)
    private List<Integer> rankCardCondition = new ArrayList<>(); // Order of highest card value of poker rank. Highest -> Lowest    
    
    //private int rankCardValue;  // 1st highest card value of poker rank.
    //private int rankCardValue2; //  2nd highest card value in the hand.
    //private int rankCardValue3; // 3rd highest card value in the hand.      

    
    public void addRankCondition(int value){
        this.rankCardCondition.add(value);
    }
    
    public int getRankCardCondition(int index){
        return rankCardCondition.get(index);
    }
    
    public String[] getCardFull() {
        return Arrays.copyOf(cardFull, cardFull.length);
        //return cardFull;
    }

    public void setCardFull(String[] cardFull) {
        this.cardFull = new String[cardFull.length];
        System.arraycopy(cardFull, 0, this.cardFull, 0, cardFull.length);
        //this.cardFull = cardFull;
    }

    public int[] getCardValue() {
        return Arrays.copyOf(cardValue, cardValue.length);
        //return cardValue;
    }

    public void setCardValue(int[] cardValue) {
        this.cardValue = new int[cardValue.length];
        System.arraycopy(cardValue, 0, this.cardValue, 0, cardValue.length);
        //this.cardValue = cardValue;
    }

    public CardSuit[] getCardSuit() {
        return Arrays.copyOf(cardSuit, cardSuit.length);
        //return cardSuit;
    }

    public void setCardSuit(CardSuit[] cardSuit) {
        this.cardSuit = new CardSuit[cardSuit.length];
        System.arraycopy(cardSuit, 0, this.cardSuit, 0, cardSuit.length);
        //this.cardSuit = cardSuit;
    }


    /*public int getRankCardValue() {
        return rankCardValue;
    }

    public void setRankCardValue(int rankCardValue) {
        this.rankCardValue = rankCardValue;
    }

    public int getRankCardValue2() {
        return rankCardValue2;
    }

    public void setRankCardValue2(int rankCardValue2) {
        this.rankCardValue2 = rankCardValue2;
    }

    public int getRankCardValue3() {
        return rankCardValue3;
    }

    public void setRankCardValue3(int rankCardValue3) {
        this.rankCardValue3 = rankCardValue3;
    }
*/
    public PokerRank getRank() {
        return rank;
    }

    public void setRank(PokerRank rank) {
        this.rank = rank;
    }
    
    public List<Integer> getRankCardCondition() {
        return rankCardCondition;
    }

    public void setRankCardCondition(List<Integer> rankCardCondition) {
        this.rankCardCondition = rankCardCondition;
    }
}
