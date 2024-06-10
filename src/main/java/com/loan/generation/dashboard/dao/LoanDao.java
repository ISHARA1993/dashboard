package com.loan.generation.dashboard.dao;

import com.loan.generation.dashboard.entity.LoanApplication;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface LoanDao  extends CrudRepository<LoanApplication, Long> {

    @Query("SELECT COUNT(a) FROM LoanApplication a WHERE a.companyUen=?1 ")
    Integer checkCompanyUENCount(String uenNumber);
}
