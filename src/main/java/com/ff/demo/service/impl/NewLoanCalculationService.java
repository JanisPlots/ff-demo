package com.ff.demo.service.impl;


import com.ff.demo.dal.model.AuditModel;
import com.ff.demo.dal.model.Loan;
import com.ff.demo.dal.model.LoanVersion;
import com.ff.demo.service.ICalculationService;
import com.ff.demo.service.model.LoanContext;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;

@Service
public class NewLoanCalculationService implements ICalculationService {
    @Override
    public void doCalculate(LoanContext loanContext) {

        Loan loan = loanContext.getLoan();
        LoanVersion loanVersion = Collections.max(
                loan.getLoanVersions(),
                Comparator.comparing(AuditModel::getCreatedAt)
        );
        loanVersion.setInterestFactor(loanContext.getInterestFactor());
    }
}
