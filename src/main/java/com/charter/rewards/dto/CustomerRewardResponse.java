package com.charter.rewards.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRewardResponse {
    private Long customerId;
    private String customerName;
    private List<MonthlyRewards> monthlyRewards;
    private Long totalRewards;
}
