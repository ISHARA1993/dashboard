package com.loan.generation.dashboard.service;

import com.loan.generation.dashboard.entity.LoanApplication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LoanService {
    Object createLoan(LoanApplication loanApplication);

    Object updateLoan(LoanApplication updateLoanApplication);

    List<LoanApplication> getAllLoan();

    LoanApplication getById(long id);
}
