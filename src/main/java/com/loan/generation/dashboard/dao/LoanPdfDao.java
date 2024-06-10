package com.loan.generation.dashboard.dao;

import com.loan.generation.dashboard.entity.LoanPdfGeneration;
import org.springframework.data.repository.CrudRepository;


public interface LoanPdfDao extends CrudRepository<LoanPdfGeneration, Long> {

}
