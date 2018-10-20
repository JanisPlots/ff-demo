package com.ff.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LoanEngineDefinition {

    private List<IUnderwritingRule> underwritingRules;
    private ICalculationService iCalculationService;

    public LoanEngineDefinition(
            List<IUnderwritingRule> underwritingRules,
            ICalculationService iCalculationService
    ){
        this.underwritingRules = underwritingRules;
        this.iCalculationService = iCalculationService;
    }

    public List<IUnderwritingRule> getUnderwritingRules() {
        return underwritingRules;
    }

    public ICalculationService getiCalculationService() {
        return iCalculationService;
    }
}
