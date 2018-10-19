package com.ff.demo.service.impl.uwrules;

import com.ff.demo.dal.model.Loan;
import com.ff.demo.service.IUnderwritingRule;
import com.ff.demo.service.model.LoanContext;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UWMaxLoansValidator implements IUnderwritingRule {

    @Override
    public void doValidate(LoanContext loanContext) {
        Calendar calYesterday = Calendar.getInstance();
        calYesterday.setTime(loanContext.getDateNow().toDate());
        calYesterday.add(Calendar.DATE, -1);

        List<Loan> daysLoans = loanContext
                .getAllLoans()
                .stream()
                .filter(l -> l.getCreatedAt().after(calYesterday.getTime()))
                .collect(Collectors.toList());


        loanContext.setRejected(daysLoans.size() >= loanContext.getMaxLoansPerDay());
    }
}
