package com.loan.generation.dashboard.response;

import com.loan.generation.dashboard.entity.LoanApplication;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoResponse {

    private long loanAppId;
    private long loGenId;
    private LoanApplication loanApplication;




}
