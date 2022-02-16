package com.hestingames.impsadventuresim.simulator;

import com.hestingames.impsadventuresim.Interface.RewardPositionCondition;
import com.hestingames.impsadventuresim.Interface.IPosition;
import com.hestingames.impsadventuresim.enums.*;
import com.hestingames.impsadventuresim.enums.ERewardPositionType;
import com.hestingames.impsadventuresim.expressions.Operator;
import com.hestingames.impsadventuresim.expressions.conditions.*;
import com.hestingames.impsadventuresim.positions.*;
import com.hestingames.impsadventuresim.utils.RewardFunction;

import java.util.ArrayList;
import java.util.LinkedList;

public class Board {
	Player player;
	IPosition[] board;
	final int startPosition;
	final int normalDices;
	final int specialDices;
	final int initialStars;
	final int initialMushrrom1;
	final int initialMushrrom2;
	final int initialMushrrom3;
    int currentPosition;
    ETarotEffectType activeTarot;
	
	public Board(int initialStars, int initialDices, int initialSpecial, boolean isSmart, int initialPosition, int initialMushrrom1, int initialMushrrom2, int initialMushrrom3, ETarotEffectType activeTarot,
				 LinkedList<StrategyEnum> activeStrategies) {
		player = new Player(initialStars, initialDices, initialSpecial, isSmart, activeTarot, initialPosition);
        board = new IPosition[20];

        normalDices = initialDices;
        specialDices = initialSpecial;
        this.initialStars = initialStars;
        this.initialMushrrom1 = initialMushrrom1;
        this.initialMushrrom2 = initialMushrrom2;
        this.initialMushrrom3 = initialMushrrom3;
        this.activeTarot = activeTarot;

        board[0] = this.getRewardPosition(ERewardPositionType.Spirit, 1, 650, 1500, 2000);
		board[1] = this.getRewardPosition(ERewardPositionType.PromotionStone, 1,  500, 1500, 2000);
		board[2] = this.getRewardPosition(ERewardPositionType.MagicDust, 1, 500, 1000, 1500);
		board[3] = this.getRewardPosition(ERewardPositionType.Stars, initialMushrrom1, 3, 4, 5);
		board[4] = new NewDice();
		board[5] = this.getRewardPosition(ERewardPositionType.Shards3stars, 1, 20, 40, 60);
		board[6] = this.getRewardPosition(ERewardPositionType.Spirit, 1, 650, 1500, 2000);
		board[7] = this.getRewardPosition(ERewardPositionType.MonsterSoul, 1, 500, 1000, 1500);
		board[8] = this.getRewardPosition(ERewardPositionType.MagicDust, 1, 500, 1000, 1500);
		board[9] = new TarotPosition();
		board[10] = this.getRewardPosition(ERewardPositionType.Stars, initialMushrrom2,  3, 4, 5);
		board[11] = this.getRewardPosition(ERewardPositionType.PromotionStone, 1, 500, 1500, 2000);
		board[12] = this.getRewardPosition(ERewardPositionType.Shards5stars, 1, 10, 20, 30);
		board[13] = this.getRewardPosition(ERewardPositionType.Spirit, 1, 650, 1500, 2000);
		board[14] = new KarmaDice();
		board[15] = this.getRewardPosition(ERewardPositionType.MagicDust, 1, 500, 1000, 1500);
		board[16] = this.getRewardPosition(ERewardPositionType.Shards4stars, 1, 10, 20, 30);
		board[17] = this.getRewardPosition(ERewardPositionType.Stars, initialMushrrom3, 3, 4, 5);
		board[18] = this.getRewardPosition(ERewardPositionType.ChaosStone, 1, 100, 200, 300);
		board[19] = new SpecialDice();

        startPosition = initialPosition;
        // Build player strategy
		// BASE STRATEGIES
//		DiceAmountSelector baseGreedy = new DiceAmountSelector(player, DiceType.SpecialDice) {
//			@Override
//			public int get() {
//				return 6;
//			}
//		};
		DiceAmountSelector normalDice = new DiceAmountSelector(player, DiceType.NormalDice, StrategyEnum.NormalDice) {
			@Override
			public int get() {
				return defaultNormal();
			}
		};

		// SMART STRATEGIES
		DiceAmountSelector baseSmart = new DiceAmountSelector(player, DiceType.SpecialDice, StrategyEnum.BaseSmart) {
			@Override
			public int get() {
				return 6;
			}
		};
		baseSmart.setCondition(
			new OrCondition(
				new AndCondition(
					new PlayerDiceAmountCondition(player, DiceType.SpecialDice, Operator.Equal, 1),
					new EvaluateField(this, 6, EPositionType.Stars)
				),
				new PlayerDiceAmountCondition(player, DiceType.SpecialDice, Operator.Greater, 1)
			)
		);

		DiceAmountSelector lookForNormalDices = new GoToPosition(player, this, EPositionType.DiceHouse, StrategyEnum.LookForNormalDices);
		lookForNormalDices.setNext(baseSmart);

		DiceAmountSelector lookForDices = new GoToPosition(player, this, EPositionType.SpecialDiceHouse, StrategyEnum.LookForDices);
		normalDice.setNext(lookForNormalDices);
		//normalDice.setNext(baseGreedy);
		lookForDices.setNext(normalDice);

		DiceAmountSelector useLuckyWithSpecial = new DiceAmountSelector(player, DiceType.SpecialDice, StrategyEnum.UseLuckyWithSpecial) {
			@Override
			public int get() {
				return 5;
			}
		};
		useLuckyWithSpecial.setCondition(new PlayerCardCondition(player, ETarotEffectType.LuckyCard));
		useLuckyWithSpecial.setNext(lookForDices);

		DiceAmountSelector outFromKarma = new DiceAmountSelector(player, DiceType.SpecialDice, StrategyEnum.OutFromKarma) {
			@Override
			public int get() {
				return 6;
			}
		};
		outFromKarma.setCondition(new AndCondition(
				new KarmaCondition(player),
				new PlayerDiceAmountCondition(player, DiceType.SpecialDice, Operator.GreaterEqual, 2)
		));
		outFromKarma.setNext(useLuckyWithSpecial);

		DiceAmountSelector notSpecialIn18 = new DiceAmountSelector(player, DiceType.NormalDice, StrategyEnum.NotSpecialIn18) {
			@Override
			public int get() {
				return defaultNormal();
			}
		};
		notSpecialIn18.setCondition(
					new CurrentPlayerCellCondition(player, 18)
		);
		notSpecialIn18.setNext(outFromKarma);

		for (StrategyEnum str : activeStrategies) {
			notSpecialIn18.setActive(true, str);
		}

		player.setStrategy(notSpecialIn18);

        GlobalEffects.Set(this);
	}

	public void SendPlayerHome() {
	    currentPosition = -1;
	    player.setPosition(currentPosition);
//	    System.out.println("El player ha regresado a la posicion -1");
    }

    public void DeactivatePlayerStrategy(boolean status, StrategyEnum name) {
		player.deactivateStrategy(status, name);
	}

    public ArrayList<RewardPosition> GetWorkshops(RewardPositionCondition cmp) {
        ArrayList<RewardPosition> group = new ArrayList<>();
        for (IPosition pos : board) {
            if(pos instanceof RewardPosition) {
                RewardPosition rewardPosition = (RewardPosition)pos;
                if(cmp.check(rewardPosition))
                    group.add(rewardPosition);
            }
        }
        return group;
    }

    public IPosition[] GetBoard() {
		return board;
	}

	public int PlayerPosition() {
		return currentPosition;
	}

	public void StartSimulation()
	{
        currentPosition = startPosition;
        int throwResult = 0;
//        while (player.HasDice() || player.HasSpecialDice()) {
		while(player.CanPlay()) {
//        	if(player.HasSpecialDice())
//        	{
//        		if(!player.IsSmart() || !player.HasDice())
//        		{
//        			// TODO Maybe add feature to try to level up specific positions
//        			specialUsed = 6;
//        			player.SpecialDiceThrow();
//        		}
//        		else if(!player.karma) {
//	        		for (int j = currentPosition + 1; j <= currentPosition + 6; j++) {
//						if(board[j % board.length] instanceof SpecialDice)
//						{
//							player.SpecialDiceThrow();
//							specialUsed = j - currentPosition;
//						}
//					}
//        		}
//        	}

//			System.out.println("Estas en la casilla " + currentPosition);
//
//        	if(specialUsed == 0)
//        		throwResult = player.ThrowDice();
//        	else {
//        		throwResult = specialUsed;
//				System.out.println("Usas un dado especial, tirando un " + throwResult);
//        		specialUsed = 0;
//    		}
			throwResult = player.ThrowDice();
			if(throwResult < 0)
        	{
				currentPosition = (board.length + currentPosition + throwResult) % board.length;
        	}
        	else
        	{
				for (int j = 0; j < throwResult - 1; j++) {
					currentPosition = (currentPosition + 1) % board.length;
					board[currentPosition].OnPassOver(player);
				}
				currentPosition = (currentPosition + 1) % board.length;
				board[currentPosition].OnGetInto(player);
        	}
			player.setPosition(currentPosition);
//			System.out.println("Llegas a la casilla " + currentPosition);
		}
		player.ConvertDicesToStars();
	}
	
	private RewardPosition getRewardPosition(ERewardPositionType type, int initialLevel, final int level1, final int level2, final int level3) {
		return new RewardPosition(type, initialLevel, new int[] { 0, level1, level2, level3 });
	}
	
	public SimulationStats GetFinalStatistics() {
		return new SimulationStats(player.turn, player.resources, player.receivedCards);
	}
	
	public void RestartBoard() {
		board[0].Restart(1);
		board[1].Restart(1);
		board[2].Restart(1);
		board[3].Restart(initialMushrrom1);
		board[4].Restart(1);
		board[5].Restart(1);
		board[6].Restart(1);
		board[7].Restart(1);
		board[8].Restart(1);
		board[9].Restart(1);
		board[10].Restart(initialMushrrom2);
		board[11].Restart(1);
		board[12].Restart(1);
		board[13].Restart(1);
		board[14].Restart(1);
		board[15].Restart(1);
		board[16].Restart(1);
		board[17].Restart(initialMushrrom3);
		board[18].Restart(1);
		board[19].Restart(1);

		player.Restart(initialStars, normalDices, specialDices, activeTarot, startPosition);
	}
}
