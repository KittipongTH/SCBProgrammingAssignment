/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scb.assignment1.process;

import com.scb.assignment1.model.PlayerModel;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kittipong.TH
 */
public class RankUtilTest {
   
    @Test
    public void testCheckStraightFlush() throws Exception {
        System.out.println("checkStraightFlush");
        PlayerModel player = PokerUtil.getPlayerData("Somchai: 2D 3D 4D 5D 6D");
        boolean expResult = true;
        boolean result = RankUtil.checkStraightFlush(player);
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckFourOfAKind() throws Exception {
        System.out.println("checkFourOfAKind");
        PlayerModel player = PokerUtil.getPlayerData("Somchai: 2D 4C 4S 4H 4D");
        boolean expResult = true;
        boolean result = RankUtil.checkFourOfAKind(player);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testCheckFullHourse() throws Exception {
        System.out.println("checkFullHourse");
        PlayerModel player = PokerUtil.getPlayerData("Somchai: 2D 4C 4S 4H 2D");
        boolean expResult = true;
        boolean result = RankUtil.checkFullHourse(player);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testCheckFlush() throws Exception {
        System.out.println("checkFlush");
        PlayerModel player = PokerUtil.getPlayerData("Somchai: 2D 4D AD 3D TD");
        boolean expResult = true;
        boolean result = RankUtil.checkFlush(player);
        assertEquals(expResult, result);
    }  
    
    @Test
    public void testCheckStraight() throws Exception {
        System.out.println("checkStraight");
        PlayerModel player = PokerUtil.getPlayerData("Somchai: TD 9D JD QD KD");
        boolean expResult = true;
        boolean result = RankUtil.checkStraight(player);
        assertEquals(expResult, result);
    }  
    
    @Test
    public void testCheckThreeOfAKind() throws Exception {
        System.out.println("checkThreeOfAKind");
        PlayerModel player = PokerUtil.getPlayerData("Somchai: 2D 2H JD TD 2S");
        boolean expResult = true;
        boolean result = RankUtil.checkThreeOfAKind(player);
        assertEquals(expResult, result);
    }  
    
    @Test
    public void testCheckTwoPairs() throws Exception {
        System.out.println("checkTwoPairs");
        PlayerModel player = PokerUtil.getPlayerData("Somchai: 2D 2H JD JS TS");
        boolean expResult = true;
        boolean result = RankUtil.checkTwoPairs(player);
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckPair() throws Exception {
        System.out.println("checkPair");
        PlayerModel player = PokerUtil.getPlayerData("Somchai: 2D 2H TD QS TS");
        boolean expResult = true;
        boolean result = RankUtil.checkPair(player);
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckHighCard() throws Exception {
        System.out.println("checkHighCard");
        PlayerModel player = PokerUtil.getPlayerData("Somchai: 2D 7H 4D 9S 3S");
        boolean expResult = true;
        boolean result = RankUtil.checkHighCard(player);
        assertEquals(expResult, result);
    }    
    
}
