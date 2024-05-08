package com.loan.generation.dashboard.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name = "Loan_Gen_tab")
public class LoanPdfGeneration {


    @Id
    @GeneratedValue
    @Column(name = "loan_gen_id")
    private long loanGenId;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "loan_app_id")
    @PrimaryKeyJoinColumn
    private LoanApplication loanApplication;

    public LoanPdfGeneration(LoanApplication loanApplication) {
        this.loanApplication = loanApplication;
    }
}
