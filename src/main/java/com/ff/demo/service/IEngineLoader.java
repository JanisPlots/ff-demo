package com.ff.demo.service;

import com.ff.demo.service.model.LoanContext;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface IEngineLoader {

    LoanEngineDefinition loadEngine(LoanContext loanContext);
}
