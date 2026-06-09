package com.charter.rewards.util;

public final class RewardCalculator {

    private RewardCalculator() {
    }
    public static long calculateRewardPoints(Long amount){
       long points = 0;
       if(amount > 100){
           points += (long)((amount -100) * 2);
           points +=50;
       }
       else if(amount > 50){
           points += (long) (amount - 50);
       }
       return points;
    }
}
