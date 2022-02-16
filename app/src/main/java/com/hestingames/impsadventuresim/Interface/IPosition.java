/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hestingames.impsadventuresim.Interface;

import com.hestingames.impsadventuresim.enums.EPositionType;
import com.hestingames.impsadventuresim.simulator.Player;

/**
 *
 * @author Youkai
 */
public interface IPosition {
    void OnPassOver(Player p);
    void OnGetInto(Player p);
    void Restart(int p);
    EPositionType getType();
}
