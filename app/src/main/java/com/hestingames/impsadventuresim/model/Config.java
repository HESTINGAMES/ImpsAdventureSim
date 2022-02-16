package com.hestingames.impsadventuresim.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.hestingames.impsadventuresim.R;

public class Config {
    private Integer simulationsNumber;
    private Boolean isSmartThrow;
    private Boolean isNotIn19;
    private Boolean isSmartLuckyCard;
    private Boolean isKarmaOut;

    public Config(){

    }

    public Config(Integer simulationsNumber, Boolean isSmartThrow, Boolean isNotIn19, Boolean isSmartLuckyCard, Boolean isKarmaOut) {
        this.simulationsNumber = simulationsNumber;
        this.isSmartThrow = isSmartThrow;
        this.isNotIn19 = isNotIn19;
        this.isSmartLuckyCard = isSmartLuckyCard;
        this.isKarmaOut = isKarmaOut;
    }

    public Integer getSimulationsNumber() {
        return simulationsNumber;
    }

    public void setSimulationsNumber(Integer simulationsNumber) {
        this.simulationsNumber = simulationsNumber;
    }

    public Boolean getSmartThrow() {
        return isSmartThrow;
    }

    public void setSmartThrow(Boolean smartThrow) {
        isSmartThrow = smartThrow;
    }

    public Boolean getNotIn19() {
        return isNotIn19;
    }

    public void setNotIn19(Boolean notIn19) {
        isNotIn19 = notIn19;
    }

    public Boolean getSmartLuckyCard() {
        return isSmartLuckyCard;
    }

    public void setSmartLuckyCard(Boolean smartLuckyCard) {
        isSmartLuckyCard = smartLuckyCard;
    }

    public Boolean getKarmaOut() {
        return isKarmaOut;
    }

    public void setKarmaOut(Boolean karmaOut) {
        isKarmaOut = karmaOut;
    }
}
