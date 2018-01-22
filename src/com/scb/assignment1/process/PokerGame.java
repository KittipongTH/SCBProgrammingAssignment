/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scb.assignment1.process;

import com.scb.assignment1.model.PlayerModel;
import com.scb.assignment1.model.ResultModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Kittipong.TH
 */
public class PokerGame {
    
    
    public void start() throws Exception {
        // Start
        System.out.println("Please insert input data ");
        System.out.println("Input format => name : card1 card2 card3 card4 card5");  
        
        // Input data
        boolean isWait = true;
        PlayerModel player1 = new PlayerModel();
        PlayerModel player2 = new PlayerModel();
        while(isWait){
            player1 = getPlayerInfo(1);
            player2 = getPlayerInfo(2);
            if(checkDuplicateCard(player1.getCard().getCardFull(), player2.getCard().getCardFull())){
                System.out.println("Please try again");
            }else{
                isWait = false;
            }
        }
        
        // Calculate Poker rank
        PokerUtil.findPokerRank(player1);
        PokerUtil.findPokerRank(player2);
        
        // Compare
        ResultModel result = PokerUtil.compareRankPlayer(player1, player2);
        
        // Print result
        printResult(result, player1, player2);
    }

    public boolean again() throws Exception {
        System.out.println();
        System.out.println("Run again please type any characters OR Exit \"E\"");
        Scanner userInput = new Scanner(System.in);
        if (userInput.nextLine().equals("E")) {
            System.exit(0);
        }
        return true; 
    }
    

    public PlayerModel getPlayerInfo(int playerNum) throws Exception{
        boolean isWait = true; 
        Scanner userInput;
        String temp;
        PlayerModel player = new PlayerModel();
        while (isWait) {
            System.out.println("Please insert Player " + playerNum);
            userInput = new Scanner(System.in);
            temp = userInput.nextLine();
            temp = temp.trim();            
            if(PokerUtil.validateInput(temp)){
                player = PokerUtil.getPlayerData(temp);
                isWait = false;
            }
        } 
        return player;
    }    
    
    private boolean checkDuplicateCard(String[] cardPlayer1, String[] cardPlayer2){
        List<String> card = new ArrayList<>();
        String[] allCard = new String[cardPlayer1.length + cardPlayer2.length];
        System.arraycopy(cardPlayer1, 0, allCard, 0, cardPlayer1.length);
        System.arraycopy(cardPlayer2, 0, allCard, cardPlayer1.length, cardPlayer2.length);
        
        for(String c : allCard){
            if(card.contains(c)){
                System.out.println("Dupicate card " + c);
                return true;
            }else{
                card.add(c);
            }
        }
        return false;
    }    
    
    private void printResult(ResultModel result, PlayerModel player1, PlayerModel player2){
        switch (result.getWinStatus()){
            case TIE : System.out.println("Tie"); break;
            case PLAYER1 : printWinPlayer(player1.getName(), result.isIsCardWin(), 
                    result.isIsSuitWin(), player1.getCard().getRank().name, 
                    result.getDesc());break;
            case PLAYER2 : printWinPlayer(player2.getName(), result.isIsCardWin(), 
                    result.isIsSuitWin(), player2.getCard().getRank().name, 
                    result.getDesc());break;
            default: System.out.println("*****");
        }
    }
    
    private void printWinPlayer(String name, boolean isCardWin, boolean isSuitWin, String rankName, String desc){
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(" wins. with ");
        sb.append(rankName);
        if(isCardWin) sb.append(" card : ");
        if(isSuitWin) sb.append(" suit : ");
        if((isCardWin || isSuitWin)) sb.append(desc);
        System.out.println(sb.toString());
    }
}
