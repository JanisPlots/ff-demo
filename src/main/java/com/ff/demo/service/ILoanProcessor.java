package com.ff.demo.service;

import com.ff.demo.service.model.LoanResult;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public interface ILoanProcessor {

    LoanResult doApply(String clientIP, BigDecimal amount, int termDays);

    LoanResult doExtend(Long loanId);
}
