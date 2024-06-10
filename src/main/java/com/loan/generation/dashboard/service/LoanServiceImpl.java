package com.loan.generation.dashboard.service;


import com.loan.generation.dashboard.dao.LoanDao;
import com.loan.generation.dashboard.dao.LoanPdfDao;
import com.loan.generation.dashboard.entity.LoanApplication;
import com.loan.generation.dashboard.entity.LoanPdfGeneration;
import com.loan.generation.dashboard.response.FailedResponse;
import com.loan.generation.dashboard.util.KafkaUtil;
import com.loan.generation.dashboard.util.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.function.LongFunction;
import java.util.function.Predicate;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanDao loanDao;

    @Autowired
    private LoanPdfDao loanPdfDao;

    public static final Logger logger = LoggerFactory.getLogger(LoanServiceImpl.class);



    @Override
    @Transactional
    public Object createLoan(LoanApplication loanApplication) {
        logger.info("LoanServiceImpl createLoan Start:{}", loanApplication);
        try {
            if (Objects.nonNull(loanApplication) && (Objects.nonNull(loanApplication.getCompanyUen()) && !loanApplication.getCompanyUen().isBlank())) {
                boolean isUENAvailable = checkHENExist.test(loanApplication.getCompanyUen());
                logger.info("isUENAvailable :{}", isUENAvailable);
                if (!isUENAvailable) {
                    LoanApplication saveLoan = loanDao.save(loanApplication);
                    String loRefNumber = "LO_BTL_" + saveLoan.getLoanAppId();
                    loanPdfDao.save(new LoanPdfGeneration(loRefNumber, saveLoan));
                    return saveLoan;
                } else {
                    logger.error("Company UEN Exist in DB");
                    return new FailedResponse(ResponseCode.FAILED_CODE_01, ResponseCode.FAILED_CODE_01_DESC);

                }
            }
        } catch (Exception e) {
            logger.error(e.toString());
            return new FailedResponse(ResponseCode.FAILED_CODE_02, ResponseCode.FAILED_CODE_02_DESC);
        }
        return null;
    }


    @Override
    public Object updateLoan(LoanApplication updateLoanApplication) {
        logger.info("LoanServiceImpl updateLoan Start:{}", updateLoanApplication);
        try {
            if (Objects.nonNull(updateLoanApplication) && updateLoanApplication.getLoanAppId() != 0) {
                if (Objects.nonNull(getLoanApplication.apply(updateLoanApplication.getLoanAppId()))) {
                    return loanDao.save(updateLoanApplication);
                } else {
                    return new FailedResponse(ResponseCode.FAILED_CODE_03, ResponseCode.FAILED_CODE_03_DESC);
                }
            } else {
                return new FailedResponse(ResponseCode.FAILED_CODE_04, ResponseCode.FAILED_CODE_04_DESC);
            }
        } catch (Exception e) {
            logger.error(e.toString());
            return new FailedResponse(ResponseCode.FAILED_CODE_02, ResponseCode.FAILED_CODE_02_DESC);
        }

    }

    @Override
    public List<LoanApplication> getAllData() {
        List<LoanApplication> loanApplications= (List<LoanApplication>) loanDao.findAll();
        return loanApplications;
    }


    Predicate<String> checkHENExist = uenNumber -> (loanDao.checkCompanyUENCount(uenNumber) != 0);


    LongFunction<LoanApplication> getLoanApplication = loanAppId -> loanDao.findById(loanAppId).get();

}
