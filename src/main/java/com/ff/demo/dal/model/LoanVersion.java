package com.ff.demo.dal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "loan_version")
public class LoanVersion extends AuditModel {
    @Id
    @GeneratedValue(generator = "loan_version_generator")
    @SequenceGenerator(
            name = "loan_version_generator",
            sequenceName = "loan_version_sequence",
            initialValue = 1000
    )
    private Long id;

    @Column
    private int termDays;

    @ManyToOne
    @JoinColumn(name="loan_id", nullable=false)
    @JsonIgnore
    private Loan loan;

    @Enumerated(EnumType.STRING)
    @Column(length = 8)
    private LoanVersionType loanVersionType;

    @Column(columnDefinition = "decimal")
    private BigDecimal interestFactor;


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

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public LoanVersionType getLoanVersionType() {
        return loanVersionType;
    }

    public void setLoanVersionType(LoanVersionType loanVersionType) {
        this.loanVersionType = loanVersionType;
    }

    public BigDecimal getInterestFactor() {
        return interestFactor;
    }

    public void setInterestFactor(BigDecimal interestFactor) {
        this.interestFactor = interestFactor;
    }

    public enum LoanVersionType{
        INITIAL,
        EXTENDED
    }
}
