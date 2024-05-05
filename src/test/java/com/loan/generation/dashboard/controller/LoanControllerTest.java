package com.loan.generation.dashboard.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loan.generation.dashboard.entity.LoanApplication;
import com.loan.generation.dashboard.response.FailedResponse;
import com.loan.generation.dashboard.service.LoanService;
import com.loan.generation.dashboard.util.ResponseCode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoanController.class)
class LoanControllerTest {


    @MockBean
    private LoanService loanService;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void init() throws Exception {
        LoanApplication loanApplication=new LoanApplication();
        loanApplication.setCompanyUen("1232BSY");
        loanApplication.setLoanAmount(10000);

        LoanApplication newloanApplication=new LoanApplication(1,"1232BSY",10000);

        when(loanService.createLoan(loanApplication)).thenReturn(newloanApplication);
        mockMvc.perform(post("/loan-dashboard/create").contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(loanApplication))
                        //.content(.v)
                ).andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void createLoan() throws Exception {

        LoanApplication loanApplication=new LoanApplication();
        loanApplication.setCompanyUen("1232BSY");
        loanApplication.setLoanAmount(10000);

        LoanApplication newloanApplication=new LoanApplication(1,"1232BSY",10000);

        when(loanService.createLoan(loanApplication)).thenReturn(newloanApplication);
        mockMvc.perform(post("/loan-dashboard/create").contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(loanApplication))
                        //.content(.v)
                ).andExpect(status().isOk())
                .andDo(print());


        FailedResponse failedResponse = new FailedResponse(ResponseCode.FAILED_CODE_01, ResponseCode.FAILED_CODE_01_DESC);
        when(loanService.createLoan(newloanApplication)).thenReturn(failedResponse);
        mockMvc.perform(post("/loan-dashboard/create").contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(failedResponse))
                        // .content(.value("Test1234"))
                ).andExpect(status().is5xxServerError())
                .andDo(print());

    }

    @Test
    void updateLoan() throws Exception {



        LoanApplication updateLoan=new LoanApplication(1,"1232BSY",10010);
        //when(loanService.createLoan(loanApplication)).thenReturn(newloanApplication);
        when(loanService.updateLoan(updateLoan)).thenReturn(updateLoan);
        mockMvc.perform(put("/loan-dashboard/updateLoanApplication").contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(updateLoan))
                        //.content(.v)
                ).andExpect(status().isOk())
                .andDo(print());

    }
}