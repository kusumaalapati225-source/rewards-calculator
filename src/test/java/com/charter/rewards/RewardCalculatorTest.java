package com.charter.rewards;

import com.charter.rewards.util.RewardCalculator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RewardCalculatorTest {

    @Test
    void shouldReturnZeroPointsForAmountLessThan50(){
        assertEquals(0, RewardCalculator.calculateRewardPoints(Long.valueOf(40)));
    }
    @Test
    void shouldReturn25PointsFor75Dollars(){
        assertEquals(25,RewardCalculator.calculateRewardPoints(Long.valueOf(75)));
    }

    @Test
    void shouldReturn50PointsFor100Dollars(){
        assertEquals(50,RewardCalculator.calculateRewardPoints(Long.valueOf(100)));
    }

    @Test
    void shouldReturn90PointsFor120Dollars(){
        assertEquals(90,RewardCalculator.calculateRewardPoints(Long.valueOf(120)));
    }
    @Test
    void shouldReturn250PointsFor200Dollars(){
        assertEquals(250,RewardCalculator.calculateRewardPoints(Long.valueOf(200)));
    }
}
