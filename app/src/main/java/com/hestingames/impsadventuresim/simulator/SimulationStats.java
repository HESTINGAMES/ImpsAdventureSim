package com.hestingames.impsadventuresim.simulator;

import com.hestingames.impsadventuresim.enums.ERewardPositionType;

import java.util.HashMap;

public class SimulationStats {
	public int StarsEarned;
	public int SpiritEarned;
	public int MagicDustEarned;
	public int Shards3starsEarned;
	public int Shards4starsEarned;
	public int Shards5starsEarned;
	public int promotionStoneEarned;
	public int chaosStoneEarned;
	public int DicesThrown;
	public int MosterSoul;
	public HashMap recibedCards;
	
	
	public SimulationStats(int dicesThrown, HashMap<ERewardPositionType,  Integer> playerResources, HashMap recibedCards) {
		super();
		StarsEarned = playerResources.get(ERewardPositionType.Stars);
		SpiritEarned = playerResources.get(ERewardPositionType.Spirit);
		MagicDustEarned = playerResources.get(ERewardPositionType.MagicDust);
		Shards3starsEarned = playerResources.get(ERewardPositionType.Shards3stars);
		Shards4starsEarned = playerResources.get(ERewardPositionType.Shards4stars);
		Shards5starsEarned = playerResources.get(ERewardPositionType.Shards5stars);
		this.promotionStoneEarned = playerResources.get(ERewardPositionType.PromotionStone);
		this.chaosStoneEarned = playerResources.get(ERewardPositionType.ChaosStone);
		MosterSoul = playerResources.get(ERewardPositionType.MonsterSoul);
		DicesThrown = dicesThrown;
		this.recibedCards = recibedCards;
	}
	
	
	
}
