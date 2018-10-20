package com.ff.demo.service.impl.uwrules;

import com.ff.demo.service.IUnderwritingRule;
import com.ff.demo.service.model.LoanContext;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class UWMaxAmountValidator implements IUnderwritingRule {

    @Override
    public void doValidate(LoanContext loanContext) {

        BigDecimal maxAmount = loanContext
                .getLoan()
                .getAmount();

        boolean rejected = maxAmount.compareTo(loanContext.getMaxAmount()) == 1;
        if(rejected){
            loanContext.setAccepted(false);
            loanContext.setRejectionReason("Max amount exceeded");
        }

    }
}
