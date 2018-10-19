package com.ff.demo.rest.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class RestLoan {

    private Long id;
    private List<RestLoanVersion> loanVersions;
    private BigDecimal amount;
    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<RestLoanVersion> getLoanVersions() {
        return loanVersions;
    }

    public void setLoanVersions(List<RestLoanVersion> loanVersions) {
        this.loanVersions = loanVersions;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
