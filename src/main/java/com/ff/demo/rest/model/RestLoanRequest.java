package com.ff.demo.rest.model;

import java.math.BigDecimal;

public class RestLoanRequest {
    private int termDays;
    private BigDecimal amount;

    public int getTermDays() {
        return termDays;
    }

    public void setTermDays(int termDays) {
        this.termDays = termDays;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
