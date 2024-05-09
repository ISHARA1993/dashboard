package com.loan.generation.dashboard.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name = "Loan_Application_tab")
public class LoanApplication {


    @Id
    @GeneratedValue
    @Column(name = "loan_app_id")
    private long loanAppId;

    @Column(name = "COMPANY_UEN")
    private String companyUen;

    @Column(name = "loan_amount")
    private double loanAmount;

}
