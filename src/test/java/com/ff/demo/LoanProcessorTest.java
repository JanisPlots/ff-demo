package com.ff.demo;


import com.ff.demo.dal.model.Client;
import com.ff.demo.dal.model.Loan;
import com.ff.demo.dal.model.LoanVersion;
import com.ff.demo.dal.repository.ClientRepository;
import com.ff.demo.dal.repository.LoanRepository;
import com.ff.demo.dal.repository.LoanVersionRepository;
import com.ff.demo.service.ILoanProcessor;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class LoanProcessorTest extends BaseSpringTest {

    @Test
    public void applyAndExtendTest() {

        String clientIp = "1.0.0.0";
        Loan loan = null;
        BigDecimal loanAmount = BigDecimal.valueOf(100);
        {

            iLoanProcessor.doApply(clientIp, loanAmount, 12);

            Client client = clientRepository.findByIp(clientIp);

            Assert.assertNotNull("Client with IP must be created", client);

            List<Loan> loans = loanRepository.findByClientId(client.getId());
            Assert.assertEquals("Client must have one loan", 1, loans.size());

            loan = loans.get(0);
            Assert.assertEquals("Loan must be with open status", Loan.LoanStatus.OPEN, loan.getLoanStatus());

            Assert.assertEquals(
                    "Check loan amount",
                    loanAmount,
                    loan.getAmount()
            );

            List<LoanVersion> loanVersions = loan.getLoanVersions();
            Assert.assertEquals("Loan must have one version", 1, loanVersions.size());

            LoanVersion loanVersion = loanVersions.get(0);
            Assert.assertEquals(
                    "Loan version must be initial",
                    LoanVersion.LoanVersionType.INITIAL,
                    loanVersion.getLoanVersionType()
            );
        }

        {
            iLoanProcessor.doExtend(loan.getId(), 30);

            Client client = clientRepository.findByIp(clientIp);
            Assert.assertNotNull("Client with IP must be created", client);

            List<Loan> loans = loanRepository.findByClientId(client.getId());
            Assert.assertEquals("Client must have one loan", 1, loans.size());

            loan = loans.get(0);
            Assert.assertEquals("Loan must be with open status", Loan.LoanStatus.OPEN, loan.getLoanStatus());

            Assert.assertEquals(
                    "Check loan amount",
                    loanAmount,
                    loan.getAmount()
            );

            Optional<LoanVersion> extendedLoanVersionOpt = loan
                    .getLoanVersions()
                    .stream()
                    .filter(lv -> lv.getLoanVersionType().equals(LoanVersion.LoanVersionType.EXTENDED))
                    .findFirst();

            Assert.assertTrue("Extended loan version must be present", extendedLoanVersionOpt.isPresent());

            LoanVersion loanVersion = extendedLoanVersionOpt.get();
            Assert.assertEquals(
                    "Loan version must be initial",
                    LoanVersion.LoanVersionType.EXTENDED,
                    loanVersion.getLoanVersionType()
            );
        }

    }
}
