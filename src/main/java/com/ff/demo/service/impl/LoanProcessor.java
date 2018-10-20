package com.ff.demo.service.impl;

import com.ff.demo.service.*;
import com.ff.demo.service.model.LoanContext;
import com.ff.demo.service.model.LoanResult;
import com.ff.demo.util.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class LoanProcessor implements ILoanProcessor {

    @Autowired
    private IContextLoader iContextLoader;

    @Autowired
    private IEngineLoader iEngineLoader;

    @Autowired
    private IPersistenceService iPersistenceService;

    @Autowired
    private ObjectMapper mapper;


    @Override
    public LoanResult doApply(String clientIP, BigDecimal amount, int termDays) {

        LoanContext loanContext = iContextLoader.loadNewContext(clientIP, amount, termDays);

        return doProcess(loanContext);
    }

    @Override
    public LoanResult doExtend(Long loanId, int termDays) {
        LoanContext loanContext = iContextLoader.loadExtendContext(loanId, termDays);

        return doProcess(loanContext);
    }

    private LoanResult doProcess(LoanContext loanContext){

        LoanEngineDefinition engine = iEngineLoader.loadEngine(loanContext);

        for (IUnderwritingRule uwRule: engine.getUnderwritingRules()) {
            uwRule.doValidate(loanContext);

            if(!loanContext.isAccepted()){
                break;
            }
        }

        if(loanContext.isAccepted()){
            engine.getiCalculationService().doCalculate(loanContext);
        }

        iPersistenceService.doPersist(loanContext);

        return mapper.map(loanContext, LoanResult.class);

    }
}
