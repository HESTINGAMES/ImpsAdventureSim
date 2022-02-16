/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hestingames.impsadventuresim.positions;

import com.hestingames.impsadventuresim.enums.EPositionType;
import com.hestingames.impsadventuresim.simulator.Player;
import com.hestingames.impsadventuresim.Interface.IPosition;

/**
 *
 * @author Youkai
 */
public class KarmaDice implements IPosition {

    @Override
	public void Restart(int p) {
		// TODO Auto-generated method stub
		
	}

    @Override
    public EPositionType getType() {
        return EPositionType.KarmaHouse;
    }

    @Override
    public void OnPassOver(Player p) {

    }

    @Override
    public void OnGetInto(Player p) {
        p.GotKarma();
    }
    
}
