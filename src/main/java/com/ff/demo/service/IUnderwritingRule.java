package com.ff.demo.service;

import com.ff.demo.service.model.LoanContext;
import org.springframework.stereotype.Service;

@Service
public interface IUnderwritingRule {

    void doValidate(LoanContext loanContext);
}
