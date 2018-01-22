/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scb.assignment1.process;

/**
 *
 * @author Kittipong.TH
 */
public class SCBAssignment1 {
    public static void main(String args[]){
        try{
            boolean isReRun = true;
            PokerGame game;
            while (isReRun) {
                game = new PokerGame();
                game.start();
                isReRun = game.again();
            }
        }catch(Exception ex){
            System.out.println("Error, Please try again.");
            ex.printStackTrace();
        }
    }
}
