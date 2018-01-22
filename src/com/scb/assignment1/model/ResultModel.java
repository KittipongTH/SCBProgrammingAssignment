/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scb.assignment1.model;

import com.scb.assignment1.process.PokerUtil;

/**
 *
 * @author Kittipong.TH
 */
public class ResultModel {
    private PokerUtil.ResultEnum winStatus = PokerUtil.ResultEnum.TIE;
    private boolean isCardWin = false;
    private boolean isSuitWin = false;
    private String desc;

    public PokerUtil.ResultEnum getWinStatus() {
        return winStatus;
    }

    public void setWinStatus(PokerUtil.ResultEnum winStatus) {
        this.winStatus = winStatus;
    }

    public boolean isIsCardWin() {
        return isCardWin;
    }

    public void setIsCardWin(boolean isCardWin) {
        this.isCardWin = isCardWin;
    }

    public boolean isIsSuitWin() {
        return isSuitWin;
    }

    public void setIsSuitWin(boolean isSuitWin) {
        this.isSuitWin = isSuitWin;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    
}
