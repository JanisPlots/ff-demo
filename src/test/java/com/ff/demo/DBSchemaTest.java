package com.ff.demo;

import com.ff.demo.dal.model.Client;
import com.ff.demo.dal.model.Loan;
import com.ff.demo.dal.model.LoanVersion;
import com.ff.demo.dal.repository.ClientRepository;
import com.ff.demo.dal.repository.LoanRepository;
import com.ff.demo.dal.repository.LoanVersionRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DBSchemaTest extends BaseSpringTest {

	@Autowired
	private LoanRepository loanRepository;

	@Autowired
	private LoanVersionRepository loanVersionRepository;

	@Autowired
	private ClientRepository clientRepository;


	@Test
	public void dbSchemaTest() {

		Client client = new Client();
		client.setIp("127.0.0.1");

		LoanVersion loanVersion = new LoanVersion();
		loanVersion.setTermDays(1);
		loanVersion.setLoanVersionType(LoanVersion.LoanVersionType.INITIAL);

		List<LoanVersion> loanVersions = new ArrayList<>();
		loanVersions.add(loanVersion);

		Loan loan = new Loan();
		loan.setClient(client);
		loan.setAmount(BigDecimal.ONE);
		loan.setLoanVersions(loanVersions);
		loan.setLoanStatus(Loan.LoanStatus.REJECTED);
		loanVersion.setLoan(loan);

		loanRepository.save(loan);
	}

}
