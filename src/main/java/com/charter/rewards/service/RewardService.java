package com.charter.rewards.service;

import com.charter.rewards.dto.CustomerRewardResponse;

import java.util.List;

public interface RewardService {

    CustomerRewardResponse getCustomerRewards(Long customerId);
    List<CustomerRewardResponse> getAllCustomerRewards();
}
