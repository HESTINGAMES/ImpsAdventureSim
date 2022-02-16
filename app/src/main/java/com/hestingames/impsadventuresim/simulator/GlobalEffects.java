package com.hestingames.impsadventuresim.simulator;

import com.hestingames.impsadventuresim.comparers.MaxLevelLimiter;
import com.hestingames.impsadventuresim.comparers.MinLevelLimiter;
import com.hestingames.impsadventuresim.enums.ERewardPositionType;
import com.hestingames.impsadventuresim.positions.RewardPosition;

import java.util.ArrayList;
import java.util.Random;

public class GlobalEffects {
    private static Board board;
    private static Random r;

    public static void Set(Board b) {
        board = b;
        r = new Random();
    }

    public static void ReturnStart() {
        board.SendPlayerHome();
    }

    public static void DowngradeFactory() {
        ArrayList<RewardPosition> list = board.GetWorkshops(new MinLevelLimiter());
        if(!list.isEmpty())
            list.get(r.nextInt(list.size())).LowerLevel();
    }

    public static void UpgradeFactory() {
        ArrayList<RewardPosition> list = board.GetWorkshops(new MaxLevelLimiter());
        if(!list.isEmpty()) {
            list.get(r.nextInt(list.size())).RaiseLevel();
        }
    }
}
