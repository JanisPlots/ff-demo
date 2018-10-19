package com.ff.demo.service.impl;

import com.ff.demo.dal.model.Loan;
import com.ff.demo.dal.repository.LoanRepository;
import com.ff.demo.service.IPersistenceService;
import com.ff.demo.service.model.LoanContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoanPersistenceService implements IPersistenceService {

    @Autowired
    private LoanRepository loanRepository;

    @Override
    @Transactional
    public void doPersist(LoanContext loanContext) {
        Loan loan = loanContext.getLoan();

        loan.setLoanStatus(
                loanContext.isRejected() ? Loan.LoanStatus.REJECTED : Loan.LoanStatus.OPEN
        );
        loanRepository.save(loan);

    }
}
