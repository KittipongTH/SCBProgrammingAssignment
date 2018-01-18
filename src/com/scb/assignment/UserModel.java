/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scb.assignment;

/**
 *
 * @author Kittipong.TH
 */
public class UserModel {
    private String name;
    private String[] cardFull;
    private int[] cardValue;
    private char[] cardSuit;

    private int rank = 1;
    private int rankCardValue;
    private int rankCardValue2;
    private int rankCardValue3;

    // <editor-fold defaultstate="collapsed" desc="setter-getter">    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getCardFull() {
        return cardFull;
    }

    public void setCardFull(String[] cardFull) {
        this.cardFull = cardFull;
    }

    public int[] getCardValue() {
        return cardValue;
    }

    public void setCardValue(int[] cardValue) {
        this.cardValue = cardValue;
    }


    public char[] getCardSuit() {
        return cardSuit;
    }

    public void setCardSuit(char[] cardSuit) {
        this.cardSuit = cardSuit;
    }

    public int getRankCardValue() {
        return rankCardValue;
    }

    public void setRankCardValue(int rankCardValue) {
        this.rankCardValue = rankCardValue;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
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
    // </editor-fold>
    
}
