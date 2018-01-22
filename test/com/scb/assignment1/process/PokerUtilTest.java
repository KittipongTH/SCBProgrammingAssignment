/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scb.assignment1.process;

import com.scb.assignment1.model.PlayerModel;
import com.scb.assignment1.model.ResultModel;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kittipong.TH
 */
public class PokerUtilTest {
    
    @Test
    public void testValidateInputSuccess() throws Exception {
        System.out.println("validateInput Success");
        String in = "Somchai: 2H 3D 5S 9C KD";
        boolean expResult = true;
        boolean result = PokerUtil.validateInput(in);
        assertEquals(expResult, result);
    }

    /*@Test
    public void testValidateInputFail() throws Exception {
        System.out.println("validateInput Fail");
        String in = "Somchai: 2H 3D 5S 9C";
        boolean expResult = false;
        boolean result = PokerUtil.validateInput(in);
        assertEquals(expResult, result);
    }*/   
    
    
    @Test
    public void testFindPokerRank() throws Exception {
        System.out.println("findPokerRank");
        PlayerModel player = PokerUtil.getPlayerData("Somchai: 2D 3D 4D 5D 6D");
        PokerUtil.findPokerRank(player);
        assertEquals(player.getCard().getRank(), CardUtil.PokerRank.STRAIGHT_FLUSH);
    }

    
    @Test
    public void testCompareRankPlayer() throws Exception {
        System.out.println("compareRankPlayer");
        /*
        PlayerModel player1 = PokerUtil.getPlayerData("Somchai: 2H 3D 5S 9C KD");
        PlayerModel player2 = PokerUtil.getPlayerData("Somsak: 2C 3H 4S 8C AH");
        PokerUtil.findPokerRank(player1);
        PokerUtil.findPokerRank(player2);
        ResultModel result = PokerUtil.compareRankPlayer(player1, player2);
        assertEquals(PokerUtil.ResultEnum.PLAYER2, result.getWinStatus());
        */
        testCompareRankPlayer("case 1","Somchai: 2H 3D 5S 9C KD", "Somsak: 2C 3H 4S 8C AH", PokerUtil.ResultEnum.PLAYER2);
        testCompareRankPlayer("case 2","Somchai: 2H 4S 4C 2D 4H", "Somsak: 2S 8S AS QS 3S", PokerUtil.ResultEnum.PLAYER1);
        testCompareRankPlayer("case 3","Somchai: 2H 3D 5S 9C KD", "Somsak: 2C 3H 4S 8C KH", PokerUtil.ResultEnum.PLAYER1);
        testCompareRankPlayer("case 4","Somchai: 2H 3D 5S 9C KD", "Somsak: 2D 3H 5C 9S KH", PokerUtil.ResultEnum.TIE);
    }
    
    
    private void testCompareRankPlayer(String s, String p1, String p2, PokerUtil.ResultEnum expResult) throws Exception{
        
        PlayerModel player1 = PokerUtil.getPlayerData(p1);
        PlayerModel player2 = PokerUtil.getPlayerData(p2);
        PokerUtil.findPokerRank(player1);
        PokerUtil.findPokerRank(player2);
        ResultModel result = PokerUtil.compareRankPlayer(player1, player2);
        assertEquals(s,expResult, result.getWinStatus());    
    }
    
}
