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
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "loan_gen_id")
    private long loanGenId;

    @Column(name = "lo_ref_number")
    private String loRefNumber;


    @OneToOne
    //@MapsId
    @JoinColumn(name = "loan_app_id")
    private LoanApplication loanApplication;

    public LoanPdfGeneration(String loRefNumber, LoanApplication loanApplication) {
        this.loRefNumber = loRefNumber;
        this.loanApplication = loanApplication;
    }
}
