package com.ff.demo;

import com.ff.demo.dal.model.Client;
import com.ff.demo.dal.model.Loan;
import com.ff.demo.dal.model.LoanVersion;
import com.ff.demo.dal.repository.ClientRepository;
import com.ff.demo.dal.repository.LoanRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DBSchemaTest extends BaseSpringTest {


	@Test
	public void dbSchemaTest() {

		String clientIP = "127.0.0.1";
		BigDecimal loanAmount = BigDecimal.ONE;

		Client client = new Client();
		client.setIp(clientIP);

		LoanVersion loanVersion = new LoanVersion();
		loanVersion.setTermDays(1);
		loanVersion.setLoanVersionType(LoanVersion.LoanVersionType.INITIAL);

		List<LoanVersion> loanVersions = new ArrayList<>();
		loanVersions.add(loanVersion);

		Loan loan = new Loan();
		loan.setClient(client);
		loan.setAmount(loanAmount);
		loan.setLoanVersions(loanVersions);
		loan.setLoanStatus(Loan.LoanStatus.REJECTED);
		loanVersion.setLoan(loan);

		loanRepository.save(loan);


		Client dbClient = clientRepository.findByIp(clientIP);

		Assert.assertNotNull("Client must be in DB", dbClient);

		List<Loan> dbLoans = loanRepository.findByClientId(dbClient.getId());
		Assert.assertEquals("One loan must be in DB", 1, dbLoans.size());

		Loan dbLoan = dbLoans.get(0);
		Assert.assertEquals(
				"DB loan must be with rejected status",
				Loan.LoanStatus.REJECTED,
				loan.getLoanStatus()
		);

		Assert.assertEquals(
				"Check loan amount",
				loanAmount,
				loan.getAmount()
		);

		List<LoanVersion> dbLoanVersions = loan.getLoanVersions();
		Assert.assertEquals("Loan must have one version in DB", 1, dbLoanVersions.size());

		LoanVersion dbLoanVersion = loanVersions.get(0);
		Assert.assertEquals(
				"DB loan version must be initial",
				LoanVersion.LoanVersionType.INITIAL,
				dbLoanVersion.getLoanVersionType()
		);
	}

}
