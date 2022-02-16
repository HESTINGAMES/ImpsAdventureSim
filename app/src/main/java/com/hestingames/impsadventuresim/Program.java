package com.hestingames.impsadventuresim;

import com.hestingames.impsadventuresim.enums.ETarotEffectType;
import com.hestingames.impsadventuresim.enums.StrategyEnum;
import com.hestingames.impsadventuresim.simulator.Board;

public class Program {
    public static void main(String[] args) {
        /*//System.out.println("=========================================================");
        //System.out.println("=========================================================");
        //System.out.println("GAME BEGIN");
        //System.out.println("[ initialStars: 0 | initialDices: 78 | initialSpecialDices: 0 | smartThrow: true | mushrrom1: 1,  | mushrrom1: 1,  | mushrrom1: 1, activeTarot: LuckyCard");
        //System.out.println("=========================================================");
        //System.out.println("=========================================================");
        long total = 0;
        long dices = 0;
        Board b = new Board(0, 78, 0, true, -1, 1, 1, 1, null, new StrategyEnum[] { StrategyEnum.BaseSmart, StrategyEnum.NormalDice, StrategyEnum.LookForDices });
        for (int i = 0; i < 100_000; i++) {
            b.StartSimulation();
//            total += b.GetFinalStatistics().StarsEarned;
            if(b.GetFinalStatistics().StarsEarned >= 200)
                total++;
//        System.out.println("STARS: "+b.GetFinalStatistics().StarsEarned);
//        System.out.println("MAGIC DUST: "+b.GetFinalStatistics().MagicDustEarned);
//        System.out.println("MONSTER SOUL: "+b.GetFinalStatistics().MosterSoul);
//        System.out.println("SPIRIT: "+b.GetFinalStatistics().SpiritEarned);
//        System.out.println("CHAOS STONE: "+b.GetFinalStatistics().chaosStoneEarned);
//        System.out.println("PROMOTION STONE: "+b.GetFinalStatistics().promotionStoneEarned);
//        System.out.println("3* SHARDS: "+b.GetFinalStatistics().Shards3starsEarned);
//        System.out.println("4* SHARDS: "+b.GetFinalStatistics().Shards4starsEarned);
//        System.out.println("5* SHARDS: "+b.GetFinalStatistics().Shards5starsEarned);
//        System.out.println("DICES THROWS: "+b.GetFinalStatistics().DicesThrown);
            dices += b.GetFinalStatistics().DicesThrown;
            b.RestartBoard();
        }
        System.out.println("AVERAGE: " + ((double)total / 100_000f));
        System.out.println("DICES AVERAGE: " + (dices / 100_000f));
        //System.out.println("------------------------------------");
        //System.out.println("TAROT CARDS");
        //System.out.println("------------------------------------");
        for (ETarotEffectType item: ETarotEffectType.values()) {
            //System.out.println("["+item.toString()+" : "+b.GetFinalStatistics().recibedCards.get(item)+"]");
        }
        //System.out.println("------------------------------------");
        //System.out.println("------------------------------------");

        //System.out.println("=========================================================");
        //System.out.println("=========================================================");
        //System.out.println("GAME OVER");
        //System.out.println("=========================================================");
        //System.out.println("=========================================================");*/
    }
}
