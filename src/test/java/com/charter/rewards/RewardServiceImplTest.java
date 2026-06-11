package com.charter.rewards;

import com.charter.rewards.dto.CustomerRewardResponse;
import com.charter.rewards.entity.Customer;
import com.charter.rewards.entity.Transaction;
import com.charter.rewards.exception.CustomerNotFoundException;
import com.charter.rewards.repository.CustomerRepository;
import com.charter.rewards.repository.TransactionRepository;
import com.charter.rewards.serviceImpl.RewardServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RewardServiceImplTest {
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private RewardServiceImpl rewardService;

    @Test
    void shouldCalculateCustomerRewards() {
        Customer customer = Customer.builder()
                .customerId(1L)
                .customerName("John")
                .build();
        Transaction transaction = Transaction.builder()
                .transactionId(1L)
                .customerId(1L)
                .amount(BigDecimal.valueOf(120))
                .transactionDate(LocalDate.now())
                .build();

        when(customerRepository.findById(1L))
                .thenReturn(Optional.of(customer));
        when(transactionRepository.findByCustomerIdAndTransactionDateGreaterThanEqual(
                eq(1L),
                any(LocalDate.class)))
                .thenReturn(List.of(transaction));
        CustomerRewardResponse response = rewardService.getCustomerRewards(1L);
        assertNotNull(response);
        assertEquals(1L, response.getCustomerId());
        assertEquals(90L, response.getTotalRewards());
    }

    @Test
    void shouldThrowCustomerNotFoundException() {
        when(customerRepository.findById(100L))
                .thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> rewardService.getCustomerRewards(100L));
    }
}
