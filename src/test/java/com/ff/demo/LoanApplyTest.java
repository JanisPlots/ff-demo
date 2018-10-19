package com.ff.demo;


import com.ff.demo.dal.model.Client;
import com.ff.demo.dal.model.Loan;
import com.ff.demo.dal.model.LoanVersion;
import com.ff.demo.dal.repository.ClientRepository;
import com.ff.demo.dal.repository.InvoiceRepository;
import com.ff.demo.dal.repository.LoanRepository;
import com.ff.demo.dal.repository.LoanVersionRepository;
import com.ff.demo.service.ILoanProcessor;
import com.ff.demo.service.model.LoanContext;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class LoanApplyTest extends BaseSpringTest {

    @Autowired
    private ILoanProcessor iLoanProcessor;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private LoanVersionRepository loanVersionRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Test
    public void applyTest() {

        String clientIp = "0.0.0.0";
        BigDecimal loanAmount = BigDecimal.valueOf(100);

        iLoanProcessor.doApply(clientIp, loanAmount, 12);

        Client client = clientRepository.findByIp(clientIp);

        Assert.assertNotNull("Client with IP must be created",client);

        List<Loan> loans = loanRepository.findByClientId(client.getId());
        Assert.assertEquals("Client must have one loan", 1, loans.size());

        Loan loan1 = loans.get(0);
        Assert.assertEquals("Loan must be with open status", Loan.LoanStatus.OPEN, loan1.getLoanStatus());

        Assert.assertEquals(
                "Check loan amount",
                loanAmount,
                loan1.getAmount()
        );

        List<LoanVersion> loanVersions = loan1.getLoanVersions();
        Assert.assertEquals("Loan must have one version", 1, loanVersions.size());

        LoanVersion loanVersion = loanVersions.get(0);
        Assert.assertEquals(
                "Loan version must be initial",
                LoanVersion.LoanVersionType.INITIAL,
                loanVersion.getLoanVersionType()
        );

    }
}
