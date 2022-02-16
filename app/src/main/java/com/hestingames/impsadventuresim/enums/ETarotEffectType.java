package com.hestingames.impsadventuresim.enums;

import com.hestingames.impsadventuresim.simulator.Player;

import java.util.Random;

public enum ETarotEffectType {
    EnergyCard(1),
    CloudCard(2),
    LuckyCard(3),
    TrickCard(4),
    CopycatCard(5),
    BlightCard(6),
    MushroomCard(7),
    RebirthCard(8),
    PowerCard(9);

    private Integer id;

    ETarotEffectType(Integer id){
        this.id = id;
    }

    public static ETarotEffectType getById(Integer id){
        for (ETarotEffectType item: ETarotEffectType.values()) {
            if(item.id == id)
                return item;
        }
        return null;
    }

    public static ETarotEffectType getRandom(Random r) {
        ETarotEffectType[] types = ETarotEffectType.values();
        return types[r.nextInt(types.length)];
    }

    public static boolean compare(Player p, ETarotEffectType other) {
        if(p.buff == other) {
            p.buff = null;
            return true;
        }
        return false;
    }
}
