package com.loan.generation.dashboard.controller;


import com.loan.generation.dashboard.entity.LoanApplication;
import com.loan.generation.dashboard.exception.InternalServerException;
import com.loan.generation.dashboard.exception.InvalidRequestException;

import com.loan.generation.dashboard.response.FailedResponse;
import com.loan.generation.dashboard.service.LoanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Objects;


@RestController
@RequestMapping("/loan-dashboard")
public class LoanController {


    public static final Logger logger = LoggerFactory.getLogger(LoanController.class);

    @Autowired
    private LoanService loanService;


    /*
    * @http://localhost:8080/actuator
    * */
    @GetMapping("/get-status")
    public ResponseEntity<LocalDate> getStatus() {

        LocalDate today = LocalDate.now();
        logger.info("getStatus date:{}", today);
        logger.debug("debug test:{}", today);
        return ResponseEntity.status(HttpStatus.OK).body(today);
    }


    @GetMapping("/login")
    public String handleLogin(){
        return "custom_login";
    }

    @GetMapping("/user/home")
    public ResponseEntity<String> getUserAccess() {

        LocalDate today = LocalDate.now();
        logger.info("getUserAccess date:{}", today);
        return ResponseEntity.status(HttpStatus.OK).body("User :" + today);
    }

    @GetMapping("/admin/home")
    public ResponseEntity<LocalDate> getAdminHomeAccess() {

        LocalDate today = LocalDate.now();
        logger.info("getAdminHomeAccess date:{}", today);
        return ResponseEntity.status(HttpStatus.OK).body(today);
    }


    @PostMapping("/admin/create")
    public ResponseEntity<Object> createLoan(@RequestBody LoanApplication loanApplication) throws InternalServerException {
        logger.info("createLoan start :{}", loanApplication);

        Object object = loanService.createLoan(loanApplication);
        logger.info("newLoanApplication :{}", object);
        if (Objects.nonNull(object)) {
            if (object instanceof LoanApplication newLoanApplication) {
                logger.info("creation Success newLoanApplication:{} ", newLoanApplication);
                return ResponseEntity.status(HttpStatus.OK).body(newLoanApplication);
            } else if (object instanceof FailedResponse failedResponse) {
                logger.info("creation Unsuccessful Failed:{} ", failedResponse);
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(failedResponse);

            } else {
                logger.error("Creation Unsuccessful");
                throw new InvalidRequestException("loanApplication didn't created");
            }

        } else {
            logger.error("Creation Unsuccessful");
            throw new InternalServerException("loanApplication didn't created");
        }

    }

    @PutMapping("/admin/updateLoanApplication")
    public ResponseEntity<Object> updateLoan(@RequestBody LoanApplication updateLoanApplication) throws InternalServerException {
        logger.info("updateLoan start :{}", updateLoanApplication);

        Object object = loanService.updateLoan(updateLoanApplication);
        logger.info("updateLoan :{}", object);
        if (Objects.nonNull(object)) {
            if (object instanceof LoanApplication updateLoan) {
                logger.info("updateLoan Success updateLoan:{} ", updateLoan);
                return ResponseEntity.status(HttpStatus.OK).body(updateLoan);
            } else {
                logger.info("creation Unsuccessful Failed:{} ", object);
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(object);
            }

        } else {
            logger.error("updateLoan Unsuccessful");
            throw new InvalidRequestException("update Loan  didn't success");
        }

    }


}
