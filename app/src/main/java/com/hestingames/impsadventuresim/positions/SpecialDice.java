package com.hestingames.impsadventuresim.positions;

import com.hestingames.impsadventuresim.enums.DiceType;
import com.hestingames.impsadventuresim.enums.EPositionType;
import com.hestingames.impsadventuresim.simulator.Player;
import com.hestingames.impsadventuresim.Interface.IPosition;

public class SpecialDice implements IPosition {

	@Override
	public void Restart(int p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EPositionType getType() {
		return EPositionType.SpecialDiceHouse;
	}

	@Override
	public void OnPassOver(Player p) {


	}

	@Override
	public void OnGetInto(Player p) {
		p.AddDice(DiceType.SpecialDice);
	}

}
