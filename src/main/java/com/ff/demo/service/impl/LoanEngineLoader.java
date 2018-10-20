package com.ff.demo.service.impl;

import com.ff.demo.service.IEngineLoader;
import com.ff.demo.service.IUnderwritingRule;
import com.ff.demo.service.LoanEngineDefinition;
import com.ff.demo.service.model.LoanContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class LoanEngineLoader implements IEngineLoader {

    @Autowired
    private List<IUnderwritingRule> underwritingRules;


    @Autowired
    private NewLoanCalculationService newLoanCalculationService;

    @Autowired
    private ExtendLoanCalculationService extendLoanCalculationService;

    @Override
    public LoanEngineDefinition loadEngine(LoanContext loanContext) {

        switch (loanContext.getProcess()){
            case NEW:{
                return new LoanEngineDefinition(
                        underwritingRules,
                        newLoanCalculationService
                );
            }
            case EXTENDED:{
                return new LoanEngineDefinition(
                        Collections.emptyList(),
                        extendLoanCalculationService
                );
            }
            default:{
                throw new UnsupportedOperationException("Unknown loan process");
            }
        }
    }
}
