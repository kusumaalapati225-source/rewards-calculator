package com.charter.rewards.controller;

import com.charter.rewards.dto.CustomerRewardResponse;
import com.charter.rewards.service.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rewards")
@RequiredArgsConstructor
public class RewardController {
    private final RewardService rewardService;

    @GetMapping("/{customerId}")
    public CustomerRewardResponse getCustomerRewards(
            @PathVariable Long customerId) {
        return rewardService.getCustomerRewards(customerId);
    }
    @GetMapping
    public List<CustomerRewardResponse> getAllCustomerRewards() {
        return rewardService.getAllCustomerRewards();
    }
}
