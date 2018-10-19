package com.ff.demo.service.impl.uwrules;

import com.ff.demo.service.IUnderwritingRule;
import com.ff.demo.service.model.LoanContext;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;


@Component
public class UWTimeValidator implements IUnderwritingRule {

    @Override
    public void doValidate(LoanContext loanContext) {
        LocalTime timeNow = LocalDateTime.ofInstant(
                loanContext.getDateNow().toDate().toInstant(),
                ZoneId.systemDefault()
        ).toLocalTime();

        BigDecimal loanAmount = loanContext
                .getLoan()
                .getAmount();

        boolean rejected = loanAmount.compareTo(loanContext.getMaxAmount()) == 0 &&
                timeNow.isAfter(loanContext.getMaxUwTimeMaxAmount()) &&
                timeNow.isBefore(loanContext.getMinUwTimeMaxAmount());

        loanContext.setRejected(rejected);
    }
}
