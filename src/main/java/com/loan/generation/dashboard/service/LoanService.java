package com.loan.generation.dashboard.service;

import com.loan.generation.dashboard.entity.LoanApplication;
import org.springframework.stereotype.Service;

@Service
public interface LoanService {
    Object createLoan(LoanApplication loanApplication);

    Object updateLoan(LoanApplication updateLoanApplication);

    LoanApplication getLoan(long loanAppId);

}
