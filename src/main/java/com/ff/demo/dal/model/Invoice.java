package com.ff.demo.dal.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "invoice")
public class Invoice extends AuditModel {
    @Id
    @GeneratedValue(generator = "invoice_generator")
    @SequenceGenerator(
            name = "invoice_generator",
            sequenceName = "invoice_sequence",
            initialValue = 1000
    )
    private Long id;

    @Column(columnDefinition = "decimal")
    private BigDecimal amount;

    @Column
    private Date dueDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 5)
    private InvoiceStatus invoiceStatus;

    @ManyToOne
    @JoinColumn(name="loan_version_id", nullable=false)
    private LoanVersion loanVersion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public InvoiceStatus getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(InvoiceStatus invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public LoanVersion getLoanVersion() {
        return loanVersion;
    }

    public void setLoanVersion(LoanVersion loanVersion) {
        this.loanVersion = loanVersion;
    }

    public enum InvoiceStatus{
        DUE,
        PAYED
    }
}
