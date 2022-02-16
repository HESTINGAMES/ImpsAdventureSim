package com.hestingames.impsadventuresim.simulator;

import java.text.DecimalFormat;
import java.util.HashMap;

public class Stats {
	public int max;
	public int min;
	public int average;
	public Double prob80;
	public Double prob110;
	public Double prob140;
	public Double prob170;
	public Double prob200;
	public Double prob230;
	public Double prob260;
	public Double prob300;
	public Integer spiritAverage;
	public Integer mosterSoulAverage;
	public Integer promotionStoneAverage;
	public Integer magicDustAverage;
	public Integer chaosSotneAverage;
	public Integer shards3Average;
	public Integer shards4Average;
	public Integer shards5Average;
	public HashMap obtainedCards;


	
	public Stats(int max, int min, int average, Double prob80, Double prob110, Double prob140, Double prob170,
			Double prob200, Double prob230, Double prob260, Double prob300, int spirit, int magicDust, int mosterSoul, int promotionStone, int chaosStone, int shards3, int shards4, int shards5, HashMap obtainedCards) {
		super();
		this.max = max;
		this.min = min;
		this.average = average;
		this.prob80 = prob80;
		this.prob110 = prob110;
		this.prob140 = prob140;
		this.prob170 = prob170;
		this.prob200 = prob200;
		this.prob230 = prob230;
		this.prob260 = prob260;
		this.prob300 = prob300;
		this.spiritAverage = spirit;
		this.magicDustAverage = magicDust;
		this.mosterSoulAverage = mosterSoul;
		this.promotionStoneAverage = promotionStone;
		this.chaosSotneAverage = chaosStone;
		this.shards3Average = shards3;
		this.shards4Average = shards4;
		this.shards5Average = shards5;
		this.obtainedCards = obtainedCards;
	}

	public String getMax() {
		return max+"";
	}

	public void setMax(int max) {
		this.max = max;
	}

	public String getMin() {
		return min+"";
	}

	public void setMin(int min) {
		this.min = min;
	}

	public String getAverage() {
		return average+"";
	}

	public void setAverage(int average) {
		this.average = average;
	}

	public String getProb80() {
		DecimalFormat format = new DecimalFormat("00.000");
		return format.format(prob80)+"%";
	}

	public void setProb80(Double prob80) {
		this.prob80 = prob80;
	}

	public String getProb110() {
		DecimalFormat format = new DecimalFormat("00.000");
		return format.format(prob110)+"%";
	}

	public void setProb110(Double prob110) {
		this.prob110 = prob110;
	}

	public String getProb140() {
		DecimalFormat format = new DecimalFormat("00.000");
		return format.format(prob140)+"%";
	}

	public void setProb140(Double prob140) {
		this.prob140 = prob140;
	}

	public String getProb170() {
		DecimalFormat format = new DecimalFormat("00.000");
		return format.format(prob170)+"%";
	}

	public void setProb170(Double prob170) {
		this.prob170 = prob170;
	}

	public String getProb200() {
		DecimalFormat format = new DecimalFormat("00.000");
		return format.format(prob200)+"%";
	}

	public void setProb200(Double prob200) {
		this.prob200 = prob200;
	}

	public String getProb230() {
		DecimalFormat format = new DecimalFormat("00.000");
		return format.format(prob230)+"%";
	}

	public void setProb230(Double prob230) {
		this.prob230 = prob230;
	}

	public String getProb260() {
		DecimalFormat format = new DecimalFormat("00.000");
		return format.format(prob260)+"%";
	}

	public void setProb260(Double prob260) {
		this.prob260 = prob260;
	}

	public String getProb300() {
		DecimalFormat format = new DecimalFormat("00.000");
		return format.format(prob300)+"%";
	}

	public void setProb300(Double prob300) {
		this.prob300 = prob300;
	}


	public String getSpiritAverage() {
		return spiritAverage.toString();
	}

	public String getMosterSoulAverage() {
		return mosterSoulAverage.toString();
	}

	public String getPromotionStoneAverage() {
		return promotionStoneAverage.toString();
	}

	public String getMagicDustAverage() {
		return magicDustAverage.toString();
	}

	public String getChaosSotneAverage() {
		return chaosSotneAverage.toString();
	}

	public String getShards3Average() {
		return shards3Average.toString();
	}

	public String getShards4Average() {
		return shards4Average.toString();
	}

	public String getShards5Average() {
		return shards5Average.toString();
	}
}
