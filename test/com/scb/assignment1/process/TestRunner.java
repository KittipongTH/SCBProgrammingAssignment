/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scb.assignment1.process;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 *
 * @author Kittipong.TH
 */
public class TestRunner {

    public static void main(String[] args) {
        // Test RankUtil
        Result result = JUnitCore.runClasses(RankUtilTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println("Test RankUtilTest Result : " + result.wasSuccessful());
        System.out.println("\n");
        
        
        
        // Test PokerUtil
        result = JUnitCore.runClasses(PokerUtilTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println("Test PokerUtilTest Result : " + result.wasSuccessful());
        System.out.println("\n");

    }
}
