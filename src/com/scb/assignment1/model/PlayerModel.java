/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scb.assignment1.model;

/**
 *
 * @author Kittipong.TH
 */
public class PlayerModel {
    private String name;
    private CardModel card;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CardModel getCard() {
        return card;
    }

    public void setCard(CardModel card) {
        this.card = card;
    }

}
