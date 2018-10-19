package com.ff.demo.service.impl;


import com.ff.demo.dal.model.Loan;
import com.ff.demo.dal.model.LoanVersion;
import com.ff.demo.service.ICalculationService;
import com.ff.demo.service.model.LoanContext;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class LoanCalculationService implements ICalculationService {
    @Override
    public void doCalculate(LoanContext loanContext) {

        Loan loan = loanContext.getLoan();
        LoanVersion loanVersion = loan.getLoanVersions().get(loan.getLoanVersions().size()-1);

        BigDecimal amount = loan.getAmount();
        int termDays = loanVersion.getTermDays();
        BigDecimal interestFactor = loanContext.getInterestFactor();
        int paymentFrequencyDays = loanContext.getPaymentFrequencyDays();

    }
}
