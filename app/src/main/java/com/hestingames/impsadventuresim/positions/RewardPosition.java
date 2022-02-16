/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hestingames.impsadventuresim.positions;

import com.hestingames.impsadventuresim.enums.EPositionType;
import com.hestingames.impsadventuresim.enums.ERewardPositionType;
import com.hestingames.impsadventuresim.enums.ETarotEffectType;
import com.hestingames.impsadventuresim.simulator.Player;
import com.hestingames.impsadventuresim.Interface.IPosition;
import com.hestingames.impsadventuresim.utils.RewardFunction;

/**
 *
 * @author Youkai
 */
public class RewardPosition implements IPosition {
    int currentLevel = 1;
    ERewardPositionType type;
    int[] rewardPerLevel;
    
    @Override
	public void Restart(int initialLevel) {
		currentLevel = initialLevel;	
	}

    @Override
    public EPositionType getType() {
        return EPositionType.valueOf(type.name());
    }

    public RewardPosition(ERewardPositionType type, int initialLevel, int[] rewards) {
    	this.type = type;
        rewardPerLevel = rewards;
        this.currentLevel = initialLevel;
    }
    
    @Override
    public void OnPassOver(Player p) {
        p.AddResource(type, rewardPerLevel[currentLevel]);
    }

    @Override
    public void OnGetInto(Player p) {
        if(type == ERewardPositionType.Stars || !ETarotEffectType.compare(p, ETarotEffectType.CloudCard))
        	OnPassOver(p);
//        else
//            System.out.println("La nube te desgracio tu querido " + type.toString());
        
    	if(currentLevel < 3) {
            currentLevel += 1;
        }

    }
    public int CurrentLevel(){
        return currentLevel;
    }

    public void LowerLevel() {
//        System.out.println("Se ha bajado el nivel de una casilla " + type.toString());
        currentLevel -= 1;
    }

    public boolean Maxed() {
        return currentLevel == 3;
    }

    public void RaiseLevel() {
        if(currentLevel < 3) {
//            System.out.println("Se ha subido el nivel de una casilla " + type.toString());
            currentLevel += 1;
        }
    }

    public ERewardPositionType PositionType() {
    	return type;
    }
}
