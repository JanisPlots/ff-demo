package com.ff.demo.service.impl;


import com.ff.demo.dal.model.Loan;
import com.ff.demo.dal.model.LoanVersion;
import com.ff.demo.service.exception.CalculationException;
import com.ff.demo.service.ICalculationService;
import com.ff.demo.service.model.LoanContext;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ExtendLoanCalculationService implements ICalculationService {
    @Override
    public void doCalculate(LoanContext loanContext) {

        Loan loan = loanContext.getLoan();
        Optional<LoanVersion> loanVersionOpt = loan
                .getLoanVersions()
                .stream()
                .filter(lv -> lv.getLoanVersionType().equals(LoanVersion.LoanVersionType.EXTENDED))
                .findFirst();

        if(!loanVersionOpt.isPresent()){
            throw new CalculationException("Extended loan version not populated");
        }

        LoanVersion loanVersion = loanVersionOpt.get();
        int extendedTerm = loanVersion.getTermDays();
        int extensionInterestPerPeriodDays = loanContext.getExtensionInterestPerPeriodDays();

        BigDecimal extendedInterestFactorMultilayer = BigDecimal.valueOf(
                Math.ceil((double)extendedTerm/extensionInterestPerPeriodDays)
        );

        BigDecimal interestFactor = loanContext
                .getExtensionInterestFactor()
                .multiply(extendedInterestFactorMultilayer);

        loanVersion.setInterestFactor(interestFactor);

    }
}
