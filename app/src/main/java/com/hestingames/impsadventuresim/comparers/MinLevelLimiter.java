package com.hestingames.impsadventuresim.comparers;

import com.hestingames.impsadventuresim.Interface.RewardPositionCondition;
import com.hestingames.impsadventuresim.enums.ERewardPositionType;
import com.hestingames.impsadventuresim.positions.RewardPosition;

public class MinLevelLimiter implements RewardPositionCondition {
    @Override
    public boolean check(RewardPosition rewardPosition) {
        return rewardPosition.PositionType() != ERewardPositionType.Stars && rewardPosition.CurrentLevel() > 1;
    }
}
