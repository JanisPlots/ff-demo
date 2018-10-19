package com.ff.demo.service.impl.uwrules;

import com.ff.demo.service.IUnderwritingRule;
import com.ff.demo.service.model.LoanContext;
import org.springframework.stereotype.Component;

@Component
public class UWMaxAmountValidator implements IUnderwritingRule {

    @Override
    public void doValidate(LoanContext loanContext) {
        loanContext.setRejected(
                loanContext
                        .getLoan()
                        .getAmount()
                        .compareTo(loanContext.getMaxAmount()) == 1
        );
    }
}
