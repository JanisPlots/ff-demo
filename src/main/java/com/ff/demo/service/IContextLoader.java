package com.ff.demo.service;

import com.ff.demo.service.model.LoanContext;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public interface IContextLoader {

    LoanContext loadNewContext(String clientIP, BigDecimal amount, int termDays);

    LoanContext loadExistingContext(Long loanId);
}
