package com.ff.demo.dal.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "loan")
public class Loan extends AuditModel {
    @Id
    @GeneratedValue(generator = "loan_generator")
    @SequenceGenerator(
            name = "loan_generator",
            sequenceName = "loan_sequence",
            initialValue = 1000
    )
    private Long id;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name = "client_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Client client;

    @OneToMany( mappedBy = "loan", cascade={CascadeType.ALL}, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<LoanVersion> loanVersions;

    @Enumerated(EnumType.STRING)
    @Column(length = 11)
    private LoanStatus loanStatus;

    @Column(columnDefinition = "decimal")
    private BigDecimal amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<LoanVersion> getLoanVersions() {
        return loanVersions;
    }

    public void setLoanVersions(List<LoanVersion> loanVersions) {
        this.loanVersions = loanVersions;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void addVersion(LoanVersion loanVersion){
        if(loanVersions == null){
            loanVersions = new ArrayList<>();
        }
        loanVersions.add(loanVersion);
    }

    public enum LoanStatus{
        REJECTED,
        OPEN,
        CLOSED
    }

}
