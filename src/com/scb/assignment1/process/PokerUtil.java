/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scb.assignment1.process;


import com.scb.assignment1.model.PlayerModel;
import com.scb.assignment1.model.ResultModel;

/**
 *
 * @author Kittipong.TH
 */
public class PokerUtil {

    private static final String REGEX_CARD = "([\\w ]+):([ ]*[(2-9)|T|J|Q|K|A][C|D|H|S]){5}";
    
    public enum ResultEnum{
        TIE, PLAYER1, PLAYER2;
    }
    
    public static boolean validateInput(String in) throws Exception {
        try {
            if (in.matches(REGEX_CARD)) {
                return true;
            } else {
                System.out.println("Incorrect format");
                System.out.println("format input => name : card1 card2 card3 card4 card5");
                System.out.println("format card => (2, 3, 4, 5, 6, 7, 8, 9, T, J, Q, K, A)(C, D, H, S)");
                return false;
            }
        } catch (Exception ex) {
            System.err.println("Problem to validate input");
            throw ex;
        }
    }

    public static PlayerModel getPlayerData(String txt) throws Exception {
        try {
            PlayerModel player = new PlayerModel();
            String[] arr = txt.trim().split(":");
            player.setName(arr[0].trim());
            player.setCard(CardUtil.getCardData(arr[1]));
            return player;
        } catch (Exception ex) {
            System.err.println("Problem to get player info");
            throw ex;
        }
    }
    
    public static void findPokerRank(PlayerModel player) throws Exception{
        try{
            if (RankUtil.checkStraightFlush(player)) return;
            if (RankUtil.checkFourOfAKind(player)) return;
            if (RankUtil.checkFullHourse(player)) return;
            if (RankUtil.checkFlush(player)) return;
            if (RankUtil.checkStraight(player)) return;
            if (RankUtil.checkThreeOfAKind(player)) return;
            if (RankUtil.checkTwoPairs(player)) return;
            if (RankUtil.checkPair(player)) return;  
            RankUtil.checkHighCard(player);
        }catch (Exception ex) {
            System.err.println("Problem to find poker rank");
            throw ex;
        }
    }
    
    public static ResultModel compareRankPlayer(PlayerModel player1, PlayerModel player2) throws Exception{
        try{
            ResultModel result = new ResultModel();
            if(player1.getCard().getRank() != player2.getCard().getRank()){
                if (player1.getCard().getRank().value > player2.getCard().getRank().value) {
                    result.setWinStatus(ResultEnum.PLAYER1);
                    result.setDesc(player1.getCard().getRank().name);
                }else{
                    result.setWinStatus(ResultEnum.PLAYER2);
                    result.setDesc(player1.getCard().getRank().name);
                }
            }else{
                // Case same rank
                for(int i=0; i< player1.getCard().getRankCardCondition().size(); i++){
                    if(player1.getCard().getRankCardCondition(i) > player2.getCard().getRankCardCondition(i)){
                        result.setWinStatus(ResultEnum.PLAYER1);
                        result.setDesc(String.valueOf(CardUtil.convertCard(player1.getCard().getRankCardCondition(i))));
                        result.setIsCardWin(true); 
                    }else if(player1.getCard().getRankCardCondition(i) < player2.getCard().getRankCardCondition(i)){
                        result.setWinStatus(ResultEnum.PLAYER2);
                        result.setDesc(String.valueOf(CardUtil.convertCard(player2.getCard().getRankCardCondition(i))));
                        result.setIsCardWin(true); 
                    } 
                }
               
                if(result.getWinStatus()!= ResultEnum.TIE){
                    return result;
                }                 
                
                if(player1.getCard().getRank() == CardUtil.PokerRank.STRAIGHT_FLUSH){
                    // Compare suit
                    if(player1.getCard().getCardSuit()[0].code > player2.getCard().getCardSuit()[0].code){
                        result.setDesc(player1.getCard().getCardSuit()[0].name);
                    }else{
                        result.setDesc(player1.getCard().getCardSuit()[0].name);
                    }
                    result.setIsSuitWin(true);
                }else{
                    result.setWinStatus(ResultEnum.TIE);   
                }
            }
            return result;
        }catch(Exception ex){
            System.err.println("Problem to find compare rank");
            throw ex;            
        }
    
    }
    
}
