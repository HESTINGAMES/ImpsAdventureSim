package com.hestingames.impsadventuresim.simulator;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.hestingames.impsadventuresim.enums.DiceType;
import com.hestingames.impsadventuresim.enums.ERewardPositionType;
import com.hestingames.impsadventuresim.enums.ETarotEffectType;
import com.hestingames.impsadventuresim.enums.StrategyEnum;
import com.hestingames.impsadventuresim.expressions.conditions.DiceAmountSelector;
import com.hestingames.impsadventuresim.expressions.conditions.DiceCondition;

import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author Youkai
 */
public class Player {
    int turn;
    HashMap<ERewardPositionType, Integer> resources;
    ETarotEffectType activeTarot;
    
    HashMap<DiceType, Integer> amounts;
    
    boolean karma = false;
    int position;
    boolean smartPlayer;
    public Random simulator;

    public ETarotEffectType buff;

    public HashMap<ETarotEffectType, Integer> receivedCards;

    DiceAmountSelector baseStrategy;
    DiceAmountSelector strategy;
    
    public Player(int initialStars , int startDices, int specialDices, boolean isSmart, ETarotEffectType activeTarot, int position) {
    	smartPlayer = isSmart;
    	this.position = position;
    	simulator = new Random();
    	this.activeTarot = activeTarot;

        if(position == 14)
            this.karma = true;

    	receivedCards = new HashMap<>();
    	resources = new HashMap<>();
    	amounts = new HashMap<>();
        loadCardDefaultValues();
        loadResourcesDefaultValues(initialStars);
        loadDiceAmounts(startDices, specialDices);

    	this.buff = activeTarot;

        turn = 0;
    }

    public void setStrategy(DiceAmountSelector playWay) {
        baseStrategy = playWay;
    }

    public int PlayerPosition() {
        return position;
    }

    public void setPosition(int pos) {
        position = pos;
    }

    private void loadDiceAmounts(int startDices, int specialDices) {
        // TODO Make generic
        amounts.put(DiceType.NormalDice, startDices);
        amounts.put(DiceType.SpecialDice, specialDices);
    }

    public void AddResource(ERewardPositionType resource, int amount) {
        if(resource == ERewardPositionType.Stars && ETarotEffectType.compare(this, ETarotEffectType.MushroomCard)) {
            resources.put(resource, resources.get(resource) + amount * 2);
//            System.out.println("Has ganado " + amount * 2 + " de " + resource.toString() + " | Total: " + resources.get(resource));
        }
        else {
            resources.put(resource, resources.get(resource) + amount);
//            System.out.println("Has ganado " + amount + " de " + resource.toString() + " | Total: " + resources.get(resource));
        }
    }

    public void GotKarma() {
//        System.out.println("Resolviste karma");
        karma = true;
    }

    public boolean CanPlay() {
        strategy = baseStrategy.evaluate();
        return strategy != null;
    }

    public int ThrowDice() {
        turn++;
        int result = strategy.get();
        if(result > 6 || result == 0 || (amounts.get(DiceType.NormalDice) == 0 && amounts.get(DiceType.SpecialDice) == 0))
            System.out.println("asdasdasd");
        amounts.put(strategy.diceType(), amounts.get(strategy.diceType()) - 1);
        if(ETarotEffectType.compare(this, ETarotEffectType.TrickCard))
            result = -result;
        else if(karma && result % 2 == 1)
            result = -result;
        karma = false;
        if (ETarotEffectType.compare(this, ETarotEffectType.LuckyCard))
            result *= 2;
        if (ETarotEffectType.compare(this, ETarotEffectType.CopycatCard))
            result += 1 + simulator.nextInt(6);
        return result;
    }

//    public int ThrowDice()
//    {
//    	turn++;
//    	normalDices--;
//
//    	int result = 1 + simulator.nextInt(6);
//    	if(ETarotEffectType.compare(this, ETarotEffectType.TrickCard))
//            result = -result;
//    	else if(karma && result % 2 == 1)
//            result = -result;
//    	System.out.println("El resultado de la tirada fue: " + result);
//        karma = false;
//        try {
//            if (ETarotEffectType.compare(this, ETarotEffectType.LuckyCard))
//                result *= 2;
//            if (ETarotEffectType.compare(this, ETarotEffectType.CopycatCard))
//                result += 1 + simulator.nextInt(6);
//            return result;
//        }
//        finally {
//            System.out.println("El resultado final de la tirada fue: " + result);
//        }
//    }

    public void AddDice(DiceType dice) {
        amounts.put(dice, amounts.get(dice) + 1);
    }

    public boolean HasDice(DiceType dice)
    {
    	return amounts.get(dice) > 0;
    }

    public Integer DiceAmount(DiceType dice)
    {
        return amounts.get(dice);
    }

    public boolean IsSmart() {
    	return smartPlayer;
    }

    public void ConvertDicesToStars() {
        AddResource(ERewardPositionType.Stars, amounts.get(DiceType.SpecialDice) * 2);
    }

    public void Restart(int initialStars, int normal, int special, ETarotEffectType activeTarot, int startPosition) {
    	karma = false;

        if(startPosition == 14)
            this.karma = true;

        loadResourcesDefaultValues(initialStars);
    	turn = 0;
    	buff = activeTarot;
    	position = startPosition;

    	loadDiceAmounts(normal, special);
    }

    public void GiveTarotCard() {
        buff = ETarotEffectType.getRandom(simulator);
//        System.out.println("Recibiste una carta y es: " + buff.toString());
        receivedCards.put(buff, receivedCards.get(buff) + 1);

        if(ETarotEffectType.compare(this, ETarotEffectType.RebirthCard))
            GlobalEffects.ReturnStart();
        if(ETarotEffectType.compare(this, ETarotEffectType.BlightCard))
            GlobalEffects.DowngradeFactory();
        if(ETarotEffectType.compare(this, ETarotEffectType.PowerCard))
            GlobalEffects.UpgradeFactory();
    }

    private void loadCardDefaultValues(){
        for (ETarotEffectType item: ETarotEffectType.values()) {
            receivedCards.put(item, 0);
        }
    }
    private void loadResourcesDefaultValues(int initialStars){
        for (ERewardPositionType item : ERewardPositionType.values()) {
            resources.put(item, 0);
        }
        resources.put(ERewardPositionType.Stars, initialStars);
    }

    public boolean HasKarma() {
        return karma;
    }

    public void deactivateStrategy(boolean status, StrategyEnum name) {

    }
}
