package com.charter.rewards.serviceImpl;

import com.charter.rewards.dto.CustomerRewardResponse;
import com.charter.rewards.dto.MonthlyRewards;
import com.charter.rewards.entity.Customer;
import com.charter.rewards.entity.Transaction;
import com.charter.rewards.exception.CustomerNotFoundException;
import com.charter.rewards.repository.CustomerRepository;
import com.charter.rewards.repository.TransactionRepository;
import com.charter.rewards.service.RewardService;
import com.charter.rewards.util.RewardCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RewardServiceImpl implements RewardService {
    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public CustomerRewardResponse getCustomerRewards(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new CustomerNotFoundException(customerId));
        List<Transaction> transactions = transactionRepository.findByCustomerId(customerId);
        return buildRewardsResponse(customer,transactions);
    }

    @Override
    public List<CustomerRewardResponse> getAllCustomerRewards() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customer -> {
                    List<Transaction> transactions = transactionRepository.findByCustomerId(customer.getCustomerId());
                    return buildRewardsResponse(customer,transactions);
                }).collect(Collectors.toList());
    }

    private CustomerRewardResponse buildRewardsResponse(Customer customer, List<Transaction> transactions){
        Map<YearMonth , Long> monthlyReward = new TreeMap<>();
        long totalRewards = 0;
        for(Transaction transaction:transactions){
            long points = RewardCalculator.calculateRewardPoints(transaction.getAmount().longValue());
            YearMonth month = YearMonth.from(transaction.getTransactionDate());
            monthlyReward.merge(month,points,Long::sum);
            totalRewards += points;
        }
        List<MonthlyRewards> monthlyRewards = monthlyReward.entrySet().stream()
                .map(entry -> MonthlyRewards.builder()
                        .month(entry.getKey().toString())
                        .points(entry.getValue()).build()).toList();
        return CustomerRewardResponse.builder()
                .customerId(customer.getCustomerId())
                .customerName(customer.getCustomerName())
                .monthlyRewards(monthlyRewards)
                .totalRewards(totalRewards)
                .build();
    }
}
