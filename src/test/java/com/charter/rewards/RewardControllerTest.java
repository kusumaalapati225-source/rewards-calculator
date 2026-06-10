package com.charter.rewards;


import com.charter.rewards.controller.RewardController;
import com.charter.rewards.dto.CustomerRewardResponse;
import com.charter.rewards.exception.CustomerNotFoundException;
import com.charter.rewards.exception.GlobalExceptionHandler;
import com.charter.rewards.service.RewardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RewardController.class)
@Import(GlobalExceptionHandler.class)
class RewardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RewardService rewardService;

    @Test
    void shouldReturnCustomerRewards() throws Exception {

        CustomerRewardResponse response =
                CustomerRewardResponse.builder()
                        .customerId(1L)
                        .customerName("John")
                        .totalRewards(115L)
                        .build();

        when(rewardService.getCustomerRewards(1L))
                .thenReturn(response);

        mockMvc.perform(get("/api/rewards/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value(1))
                .andExpect(jsonPath("$.customerName").value("John"))
                .andExpect(jsonPath("$.totalRewards").value(115));
    }

    @Test
    void shouldReturnAllCustomerRewards() throws Exception {

        CustomerRewardResponse customer1 =
                CustomerRewardResponse.builder()
                        .customerId(1L)
                        .customerName("John")
                        .totalRewards(115L)
                        .build();

        CustomerRewardResponse customer2 =
                CustomerRewardResponse.builder()
                        .customerId(2L)
                        .customerName("Smith")
                        .totalRewards(250L)
                        .build();

        when(rewardService.getAllCustomerRewards())
                .thenReturn(List.of(customer1, customer2));

        mockMvc.perform(get("/api/rewards"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void shouldReturnNotFoundWhenCustomerDoesNotExist()
            throws Exception {

        when(rewardService.getCustomerRewards(999L))
                .thenThrow(
                        new CustomerNotFoundException(
                                100L));

        mockMvc.perform(get("/api/rewards/999"))
                .andExpect(status().isNotFound());
    }


}
