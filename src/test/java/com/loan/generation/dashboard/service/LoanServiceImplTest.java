package com.loan.generation.dashboard.service;

import com.loan.generation.dashboard.dao.LoanDao;
import com.loan.generation.dashboard.entity.LoanApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LoanServiceImplTest {

    @InjectMocks
    LoanServiceImpl loanService;

    @Mock
    LoanDao loanDao;

    @Test
    void createLoan() {
    }

    @Test
    void testCreateLoanUENAvailable(){
        LoanApplication loanApplication=new LoanApplication(1,"BSB1234",10000);
        Mockito.doReturn(1).when(loanDao).checkCompanyUENCount(loanApplication.getCompanyUen());

    }


    @Test
    void getAllLoan() {

        Mockito.doReturn(getMockLoan()).when(loanDao).findAll();
        List<LoanApplication> loanApplicationsActual=this.loanDao.findAll();
        assertEquals(2,loanApplicationsActual.size());
    }

    private List<LoanApplication> getMockLoan() {
        List<LoanApplication> loanApplications=new ArrayList<>(2);

        for(int i = 0; i< 2; i++){
            loanApplications.add(new LoanApplication(i+1,"BSB1234_"+i,10000));
        }
        return  loanApplications;
    }

    @Test
    void getById() {
    }

    @Test
    void testGetAllLoan() {
    }
}