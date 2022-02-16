package com.hestingames.impsadventuresim.positions;

import com.hestingames.impsadventuresim.enums.DiceType;
import com.hestingames.impsadventuresim.enums.EPositionType;
import com.hestingames.impsadventuresim.simulator.Player;
import com.hestingames.impsadventuresim.Interface.IPosition;

public class NewDice implements IPosition {

	@Override
	public void OnPassOver(Player p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void OnGetInto(Player p) {
		p.AddDice(DiceType.NormalDice);
		
	}

	@Override
	public void Restart(int p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EPositionType getType() {
		return EPositionType.DiceHouse;
	}

}
