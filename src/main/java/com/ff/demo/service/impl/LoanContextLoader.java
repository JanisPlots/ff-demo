package com.ff.demo.service.impl;

import com.ff.demo.dal.model.Client;
import com.ff.demo.dal.model.Loan;
import com.ff.demo.dal.model.LoanVersion;
import com.ff.demo.dal.repository.ClientRepository;
import com.ff.demo.dal.repository.LoanRepository;
import com.ff.demo.service.IContextLoader;
import com.ff.demo.service.model.LoanContext;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class LoanContextLoader implements IContextLoader {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public LoanContext loadNewContext(String clientIP, BigDecimal amount, int termDays) {

        LoanContext loanContext = new LoanContext();


        Client client = clientRepository.findByIp(clientIP);
        if(client == null){
            client = new Client();
            client.setIp(clientIP);
        }

        Loan newLoan = new Loan();
        newLoan.setAmount(amount);
        newLoan.setClient(client);

        LoanVersion newLoanVersion = new LoanVersion();
        newLoanVersion.setLoanVersionType(LoanVersion.LoanVersionType.INITIAL);
        newLoanVersion.setLoan(newLoan);
        newLoanVersion.setTermDays(termDays);
        newLoan.addVersion(newLoanVersion);

        loanContext.setLoan(newLoan);

        List<Loan> allLoans = Collections.emptyList();
        if(client.getId() != null){
            allLoans = loanRepository.findByClientId((client.getId()));
        }
        loanContext.setAllLoans(allLoans);

        loadProductDefinition(loanContext);
        loadCommons(loanContext);

        return loanContext;

    }

    @Override
    public LoanContext loadExistingContext(Long loanId) {

        Optional<Loan> loanOpt = loanRepository.findById(loanId);
        if(!loanOpt.isPresent()){
            throw new IllegalArgumentException("Loan not found");
        }

        Loan loan = loanOpt.get();

        LoanVersion newLoanVersion = new LoanVersion();
        newLoanVersion.setLoanVersionType(LoanVersion.LoanVersionType.EXTENDED);
        newLoanVersion.setLoan(loan);
        loan.addVersion(newLoanVersion);

        LoanContext loanContext = new LoanContext();
        loadProductDefinition(loanContext);

        loadCommons(loanContext);
        loanContext.setLoan(loan);

        return loanContext;
    }

    // TODO: should be loaded from configurable product definition source
    private void loadProductDefinition(LoanContext loanContext){
        loanContext.setDateNow(DateTime.now());
        loanContext.setMaxAmount(BigDecimal.valueOf(1000));
        loanContext.setMaxUwTimeMaxAmount(LocalTime.parse( "00:00:00" ));
        loanContext.setMinUwTimeMaxAmount(LocalTime.parse( "08:00:00" ));
        loanContext.setMaxLoansPerDay(3);
        loanContext.setInterestFactor(BigDecimal.valueOf(6));
        loanContext.setExtensionInterestFactorPerWeek(BigDecimal.valueOf(1.5));
        loanContext.setRejected(false);
        loanContext.setPaymentFrequencyDays(7);
    }

    private void loadCommons(LoanContext loanContext){

        Client client = loanContext.getLoan().getClient();

        List<Loan> allLoans = Collections.emptyList();
        if(client.getId() != null){
            allLoans = loanRepository.findByClientId((client.getId()));
        }
        loanContext.setAllLoans(allLoans);
    }
}
