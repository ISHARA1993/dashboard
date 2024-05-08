package com.loan.generation.dashboard.dao;

import com.loan.generation.dashboard.entity.LoanApplication;
import com.loan.generation.dashboard.entity.LoanPdfGeneration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanPdfDao extends JpaRepository<LoanPdfGeneration, Long> {

}
