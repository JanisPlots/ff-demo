package com.ff.demo.service;

import com.ff.demo.service.model.LoanContext;

public interface IPersistenceService {

    void doPersist(LoanContext loanContext);
}
