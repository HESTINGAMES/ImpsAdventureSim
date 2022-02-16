package com.hestingames.impsadventuresim.positions;

import com.hestingames.impsadventuresim.Interface.IPosition;
import com.hestingames.impsadventuresim.enums.EPositionType;
import com.hestingames.impsadventuresim.simulator.Player;

public class TarotPosition implements IPosition {
    @Override
    public void OnPassOver(Player p) {

    }

    @Override
    public void OnGetInto(Player p) {
//        System.out.println("Caiste en una casilla del tarot");
        p.GiveTarotCard();
    }

    @Override
    public void Restart(int p) {

    }

    @Override
    public EPositionType getType() {
        return EPositionType.TarotHouse;
    }
}
