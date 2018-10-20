package com.ff.demo;

import com.ff.demo.dal.repository.ClientRepository;
import com.ff.demo.dal.repository.LoanRepository;
import com.ff.demo.service.IContextLoader;
import com.ff.demo.service.ILoanProcessor;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class BaseSpringTest {

    @Autowired
    protected ILoanProcessor iLoanProcessor;

    @Autowired
    protected LoanRepository loanRepository;

    @Autowired
    protected ClientRepository clientRepository;

    @Autowired
    protected IContextLoader iContextLoader;
}
