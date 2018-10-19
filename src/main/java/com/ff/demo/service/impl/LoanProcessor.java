package com.ff.demo.service.impl;

import com.ff.demo.service.*;
import com.ff.demo.service.model.LoanContext;
import com.ff.demo.service.model.LoanResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class LoanProcessor implements ILoanProcessor {

    @Autowired
    private IContextLoader iContextLoader;

    @Autowired
    private List<IUnderwritingRule> underwritingRules;

    @Autowired
    private IPersistenceService iPersistenceService;

    @Autowired
    private NewLoanCalculationService newLoanCalculationService;

    @Autowired
    private ExtendLoanCalculationService extendLoanCalculationService;


    @Override
    public LoanResult doApply(String clientIP, BigDecimal amount, int termDays) {

        LoanContext loanContext = iContextLoader.loadNewContext(clientIP, amount, termDays);

        for (IUnderwritingRule uwRule: underwritingRules) {
            uwRule.doValidate(loanContext);

            if(loanContext.isRejected()){
                break;
            }
        }

        if(!loanContext.isRejected()){
            newLoanCalculationService.doCalculate(loanContext);
        }

        iPersistenceService.doPersist(loanContext);

        return new LoanResult(!loanContext.isRejected());
    }

    @Override
    public LoanResult doExtend(Long loanId, int termDays) {
        LoanContext loanContext = iContextLoader.loadExistingContext(loanId, termDays);

        extendLoanCalculationService.doCalculate(loanContext);

        iPersistenceService.doPersist(loanContext);

        return new LoanResult(!loanContext.isRejected());
    }
}
