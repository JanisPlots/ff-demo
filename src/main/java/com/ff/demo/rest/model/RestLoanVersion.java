package com.ff.demo.rest.model;

import com.ff.demo.dal.model.LoanVersion;

import java.math.BigDecimal;
import java.util.Date;

public class RestLoanVersion {

    private Long id;
    private int termDays;
    private LoanVersion.LoanVersionType loanVersionType;
    private BigDecimal interestFactor;
    private Date createdAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTermDays() {
        return termDays;
    }

    public void setTermDays(int termDays) {
        this.termDays = termDays;
    }

    public LoanVersion.LoanVersionType getLoanVersionType() {
        return loanVersionType;
    }

    public void setLoanVersionType(LoanVersion.LoanVersionType loanVersionType) {
        this.loanVersionType = loanVersionType;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getInterestFactor() {
        return interestFactor;
    }

    public void setInterestFactor(BigDecimal interestFactor) {
        this.interestFactor = interestFactor;
    }
}
