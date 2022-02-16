/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hestingames.impsadventuresim.positions;

import com.hestingames.impsadventuresim.Interface.IPosition;
import com.hestingames.impsadventuresim.enums.EPositionType;
import com.hestingames.impsadventuresim.simulator.Player;


/**
 *
 * @author Youkai
 */
public class EmptyPosition implements IPosition {
    
    @Override
	public void Restart(int p) {
		
	}

    @Override
    public EPositionType getType() {
        return EPositionType.StartPosition;
    }

    public EmptyPosition() {
    	
    }
    
    @Override
    public void OnPassOver(Player p) {
        
    }

    @Override
    public void OnGetInto(Player p) {
       
    }
   
}
