/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scb.assignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Kittipong.TH
 */
public class Assignment1 {

    public static void main(String[] args) {
        try {
            System.out.println("Please insert input data ");
            System.out.println("Input format => name : card1 card2 card3 card4 card5");
            boolean isReRun = true;
            while (isReRun) {
                boolean isWait = true;
                String temp = null;
                Scanner userInput = new Scanner(System.in);
                UserModel user1 = new UserModel();
                UserModel user2 = new UserModel();
                // Input data user1
                System.out.println("Please insert Player 1 :");
                while (isWait) {
                    temp = userInput.nextLine();
                    temp = temp.trim();
                    if (!validateUserInput(temp)) {
                        System.out.println("Please insert Player 1 again :");
                    } else {
                        user1 = setUserData(temp);
                        calRank(user1);
                        isWait = false;
                    }
                }

                // Input data user2
                System.out.println("Please insert Player 2 :");
                isWait = true;
                while (isWait) {
                    temp = userInput.nextLine();
                    temp = temp.trim();
                    if (!validateUserInput(temp)) {
                        System.out.println("Please insert Player 2 again :");
                    } else {
                        user2 = setUserData(temp);
                        calRank(user2);
                        isWait = false;
                    }
                }
                // Check all card
                if(!validateAllCard(user1, user2)){
                    System.out.println("Please try again");
                }else{
                    System.out.println("Calculate Result");
                    calculateResult(user1, user2);
                }
                System.out.println();
                System.out.println("Run again please type any characters OR Exit \"E\"");
                temp = userInput.nextLine();
                if (temp.equals("E")) {
                    System.exit(0);
                }
            }

        } catch (Exception ex) {
            System.out.println("Error, Please try again.");
            ex.printStackTrace();
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="user-data-input-Method">
    private static boolean validateUserInput(String in) throws Exception {
        String regEx = "([\\w ]+):"
                + "[ ]*([(2-9)|T|J|Q|K|A)])(C|D|H|S) "
                + "[ ]*([(2-9)|T|J|Q|K|A)])(C|D|H|S)"
                + "[ ]*([(2-9)|T|J|Q|K|A)])(C|D|H|S)"
                + "[ ]*([(2-9)|T|J|Q|K|A)])(C|D|H|S)"
                + "[ ]*([(2-9)|T|J|Q|K|A)])(C|D|H|S)";
        if (in.matches(regEx)) {
            return true;
        } else {
            System.out.println("Incorrect format");
            System.out.println("format input => name : card1 card2 card3 card4 card5");
            System.out.println("format card => (2, 3, 4, 5, 6, 7, 8, 9, T, J, Q, K, A)(C, D, H, S)");
            return false;
        }
    }

    private static UserModel setUserData(String txt) throws Exception {
        UserModel user = new UserModel();
        String[] arr = txt.trim().split(":");
        user.setName(arr[0].trim());
        user.setCardFull(arr[1].trim().split("\\ "));

        int[] cardValue = new int[user.getCardFull().length];
        char[] cardSuit = new char[user.getCardFull().length];

        for (int i = 0; i < user.getCardFull().length; i++) {
            cardValue[i] = convertCardValue(user.getCardFull()[i].charAt(0));
            cardSuit[i] = user.getCardFull()[i].charAt(1);
        }

        Arrays.sort(cardValue);
        Arrays.sort(cardSuit);

        user.setCardValue(cardValue);
        user.setCardSuit(cardSuit);

        //System.out.println(Arrays.toString(user.getCardFull()));
        //System.out.println(Arrays.toString(user.getCardValue()));
        //System.out.println(Arrays.toString(user.getCardSuit()));
        return user;
    }
    
    private static boolean validateAllCard(UserModel user1, UserModel user2){
        List<String> card = new ArrayList<>();
        for(String c : user1.getCardFull()){
            if(card.contains(c)){
                System.out.println("Dupicate card " + c);
                return false;
            }else{
                card.add(c);
            }
        }
        for(String c : user2.getCardFull()){
            if(card.contains(c)){
                System.out.println("Dupicate card " + c);
                return false;
            }else{
                card.add(c);
            }
        }
        return true;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="check-rank-method">
    private static void calRank(UserModel user) throws Exception {
        if (checkStraightFlush(user)) return;
        if (checkFourOfAKind(user)) return;
        if (checkFullHourse(user)) return;
        if (checkFlush(user)) return;
        if (checkStraight(user)) return;
        if (checkThreeOfAKind(user)) return;
        if (checkTwoPairs(user)) return;
        if (checkPair(user)) return;
        /*
        System.out.println("StraightFlush " + checkStraightFlush(user));
        System.out.println("FourOfAKind " + checkFourOfAKind(user));
        System.out.println("FullHourse " + checkFullHourse(user));
        System.out.println("Flush " + checkFlush(user));
        System.out.println("Straight " + checkStraight(user));
        System.out.println("ThreeOfAKind " + checkThreeOfAKind(user));
        System.out.println("TwoPairs " + checkTwoPairs(user));
        System.out.println("Pair " + checkPair(user));
         */
    }
    private static boolean checkStraightFlush(UserModel user) throws Exception {
        //Straight flush:​ 5 cards of the same suit with consecutive values. Ranked by the highest card in the hand. 
        int[] cardValue = user.getCardValue();
        for (int i = 0; i < cardValue.length - 1; i++) {
            if (cardValue[i] != cardValue[i + 1] - 1) {
                return false;
            }
        }
        if (findNumValueArr(user.getCardSuit(), user.getCardSuit()[0]) == 5) {
            user.setRank(9);
            user.setRankCardValue(cardValue[4]);
            return true;
        }
        return false;
    }

    private static boolean checkFourOfAKind(UserModel user) throws Exception {
        //Four of a kind:​ 4 cards with the same value. Ranked by the value of the 4 cards
        int[] cardValue = user.getCardValue();
        if (findNumValueArr(cardValue, cardValue[1]) == 4) {
            user.setRank(8);
            user.setRankCardValue(cardValue[1]);
            for (int c : cardValue) {
                if (c != cardValue[1]) {
                    user.setRankCardValue2(c);
                    break;
                }
            }
            //user.setRankCardValue2(0);
            return true;
        }
        return false;
    }

    private static boolean checkFullHourse(UserModel user) throws Exception {
        //Full House:​ 3 cards of the same value, with the remaining 2 cards forming a pair. Ranked by the value of the 3 cards.
        int[] cardValue = user.getCardValue();
        if ((cardValue[0] == cardValue[1]) && (cardValue[1] == cardValue[2])
                && (cardValue[3] == cardValue[4])) {
            user.setRank(7);
            user.setRankCardValue(cardValue[0]);
            user.setRankCardValue2(cardValue[3]);
            return true;
        }
        if ((cardValue[0] == cardValue[1])
                && (cardValue[2] == cardValue[3]) && (cardValue[3] == cardValue[4])) {
            user.setRank(7);
            user.setRankCardValue(cardValue[2]);
            user.setRankCardValue2(cardValue[0]);
            return true;
        }
        return false;
    }

    private static boolean checkFlush(UserModel user) throws Exception {
        //Flush:​ Hand contains 5 cards of the same suit. Hands which are both flushes are ranked using the rules for High Card.
        char[] cardSuit = user.getCardSuit();
        if (findNumValueArr(cardSuit, cardSuit[0]) == 5) {
            user.setRank(6);
            user.setRankCardValue(user.getCardValue()[4]);
            return true;
        }
        return false;
    }

    private static boolean checkStraight(UserModel user) throws Exception {
        //Straight:​ Hand contains 5 cards with consecutive values. Hands which both contain a straight are ranked by their highest card.
        int[] cardValue = user.getCardValue();
        for (int i = 0; i < cardValue.length - 1; i++) {
            if (cardValue[i] != cardValue[i + 1] - 1) {
                return false;
            }
        }
        user.setRank(5);
        user.setRankCardValue(cardValue[4]);
        return true;
    }

    private static boolean checkThreeOfAKind(UserModel user) throws Exception {
        //Three of a Kind:​ Three of the cards in the hand have the same value. Hands which both contain three of a kind are ranked by the value of the 3 cards. 
        if (findNumValueArr(user.getCardValue(), user.getCardValue()[2]) == 3) {
            user.setRank(4);
            user.setRankCardValue(user.getCardValue()[2]);
            return true;
        }
        return false;
    }

    private static boolean checkTwoPairs(UserModel user) throws Exception {
        //Two Pairs:​ The hand contains 2 different pairs. Hands which both contain 2 pairs are ranked by the value of their highest pair. 
        //Hands with the same highest pair are ranked by the value of their other pair. If these values are the same the hands are ranked by the value of the remaining card. 

        // 3 case 
        int[] cardValue = user.getCardValue();
        boolean result = false;
        if (cardValue[0] == cardValue[1] && cardValue[3] == cardValue[4]) {
            user.setRankCardValue(user.getCardValue()[3]);
            user.setRankCardValue2(user.getCardValue()[0]);
            user.setRankCardValue3(user.getCardValue()[2]);
            result = true;
        } else if (cardValue[1] == cardValue[2] && cardValue[3] == cardValue[4]) {
            user.setRankCardValue(user.getCardValue()[3]);
            user.setRankCardValue2(user.getCardValue()[1]);
            user.setRankCardValue3(user.getCardValue()[0]);
            result = true;
        } else if (cardValue[0] == cardValue[1] && cardValue[2] == cardValue[3]) {
            user.setRankCardValue(user.getCardValue()[2]);
            user.setRankCardValue2(user.getCardValue()[0]);
            user.setRankCardValue3(user.getCardValue()[4]);
            result = true;
        }
        if (result) {
            user.setRank(3);
        }
        return result;
    }

    private static boolean checkPair(UserModel user) throws Exception {
        //Pair:​ 2 of the 5 cards in the hand have the same value. Hands which both contain a pair are ranked by the value of the cards forming the pair. 
        //If these values are the same, the hands are ranked by the values of the cards not forming the pair, in decreasing order. 
        Set<Integer> temp = new HashSet<Integer>();
        for (int c : user.getCardValue()) {
            if (temp.contains(c)) {
                user.setRank(2);
                user.setRankCardValue(c);
                return true;
            } else {
                temp.add(c);
            }
        }
        return false;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="calculate">
    private static void calculateResult(UserModel user1, UserModel user2) {
        /*System.out.println("User1 Rank : " + user1.getRank());
        System.out.println("User1 Rank value : " + user1.getRankCardValue());
        System.out.println("User2 Rank : " + user2.getRank());
        System.out.println("User2 Rank value : " + user2.getRankCardValue());*/
        if (user1.getRank() != user2.getRank()) {
            // case diff rank
            compareResultRank(user1, user2);
        } else {
            // case same rank
            boolean isTie = true;
            if ((user1.getRank() != 1) && (user1.getRankCardValue() != user2.getRankCardValue())) {
                // case same rank ,diff card rank value (ingore case 1)
                compareResultRankAndValue(user1, user1.getRankCardValue(), user2, user2.getRankCardValue());
                isTie = false;
            } else {
                // case same rank and card rank value 
                switch (user1.getRank()) {
                    case 1:
                    case 6: {
                        for (int i = 4; i >= 0; i--) {
                            if (user1.getCardValue()[i] != user2.getCardValue()[i]) {
                                compareResultRankAndValue(user1, user1.getCardValue()[i], user2, user2.getCardValue()[i]);
                                isTie = false;
                                break;
                            }
                        }
                    }
                    break;
                    case 2:
                    case 4: {
                        int[] cardUser1 = removeValueInArr(user1.getCardValue(), user1.getRankCardValue());
                        int[] cardUser2 = removeValueInArr(user2.getCardValue(), user2.getRankCardValue());
                        for (int i = cardUser1.length-1; i >= 0; i--) {
                            if (cardUser1[i] != cardUser2[i]) {
                                compareResultRankAndValue(user1, cardUser1[i], user2, cardUser2[i]);
                                isTie = false;
                                break;
                            }
                        }
                    }
                    break;
                    case 3:
                    case 7: {
                        if (user1.getRankCardValue2() != user2.getRankCardValue2()) {
                            compareResultRankAndValue(user1, user1.getRankCardValue2(), user2, user2.getRankCardValue2());
                            isTie = false;
                        } else if (user1.getRankCardValue3() != user2.getRankCardValue3()) {
                            compareResultRankAndValue(user1, user1.getRankCardValue3(), user2, user2.getRankCardValue3());
                            isTie = false;
                        }
                    }
                    break;
                    case 5: {
                        if (user1.getRankCardValue() != user2.getRankCardValue()) {
                            compareResultRankAndValue(user1, user1.getRankCardValue(), user2, user2.getRankCardValue());
                            isTie = false;
                        }
                    }
                    break;
                    case 8: {
                        if (user1.getRankCardValue2() != user2.getRankCardValue2()) {
                            compareResultRankAndValue(user1, user1.getRankCardValue2(), user2, user2.getRankCardValue2());
                            isTie = false;
                        }
                    }
                    break;
                    case 9: {
                        if (user1.getRankCardValue() != user2.getRankCardValue()) {
                            compareResultRankAndValue(user1, user1.getRankCardValue(), user2, user2.getRankCardValue());
                        } else {
                            // Check suit
                            compareResultSuit(user1, user1.getCardSuit()[0], user2, user2.getCardSuit()[0]);
                        }
                    }
                    default:
                        isTie = true;
                }
            }
            if (isTie) {
                System.out.println("Tie");
            }
        }
    }

    private static void compareResultRank(UserModel user1, UserModel user2) {
        if (user1.getRank() > user2.getRank()) {
            printResultWins(user1.getName(), user1.getRank());
        } else {
            printResultWins(user2.getName(), user2.getRank());
        }
    }

    private static void compareResultSuit(UserModel user1, char user1Value, UserModel user2, char user2Value) {
        if (user1Value > user2Value) {
            printResultWins(user1.getName(), convertRank(user1.getRank()) + " suit: " + convertSuit(user1Value));
        } else {
            printResultWins(user2.getName(), convertRank(user2.getRank()) + " suit: " + convertSuit(user2Value));
        }
    }

    private static void compareResultRankAndValue(UserModel user1, int user1Value, UserModel user2, int user2Value) {
        if (user1Value > user2Value) {
            printResultWins(user1.getName(), convertRank(user1.getRank()) + " card: " + convertCard(user1Value));
        } else {
            printResultWins(user2.getName(), convertRank(user2.getRank()) + " card: " + convertCard(user2Value));
        }
    }

    private static void printResultWins(String name, int rank) {
        System.out.println(name + " wins. with " + convertRank(rank));
    }

    private static void printResultWins(String name, String desc) {
        System.out.println(name + " wins. with " + desc);
    } 
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="utility-method">
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
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="convert-method">
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
    
    private static String convertCard(int card){
        //  jack, queen, king, ace 
        switch(card){
            //case 10 : return "T";
            case 11 : return "Jack";
            case 12 : return "Queen";
            case 13 : return "King";
            case 14 : return "Ace";
            default: return String.valueOf(card);     
        }
    } 
    // </editor-fold>
    
}
