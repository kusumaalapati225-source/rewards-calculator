package com.charter.rewards.controller;

import com.charter.rewards.dto.CustomerRewardResponse;
import com.charter.rewards.service.RewardService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/rewards")
@RequiredArgsConstructor
public class RewardController {
    private final RewardService rewardService;

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerRewardResponse> getCustomerRewards(
            @PathVariable
            @Positive(message = "Customer Id must be positive")
            Long customerId) {
        return ResponseEntity.ok(rewardService.getCustomerRewards(customerId));
    }

    @GetMapping
    public ResponseEntity<List<CustomerRewardResponse>> getAllCustomerRewards() {

        return ResponseEntity.ok(rewardService.getAllCustomerRewards());
    }
}
