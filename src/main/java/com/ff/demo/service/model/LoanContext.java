package com.ff.demo.service.model;

import com.ff.demo.dal.model.Loan;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

public class LoanContext {

    private List<Loan> allLoans;
    private Loan loan;

    private boolean accepted;
    private BigDecimal maxAmount;
    private LocalTime minUwTimeMaxAmount;
    private LocalTime maxUwTimeMaxAmount;
    private int maxLoansPerDay;
    private DateTime dateNow;
    private BigDecimal interestFactor;
    private BigDecimal extensionInterestFactor;
    private int extensionInterestPerPeriodDays;
    private String rejectionReason;
    private Process process;


    public List<Loan> getAllLoans() {
        return allLoans;
    }

    public void setAllLoans(List<Loan> allLoans) {
        this.allLoans = allLoans;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    public LocalTime getMinUwTimeMaxAmount() {
        return minUwTimeMaxAmount;
    }

    public void setMinUwTimeMaxAmount(LocalTime minUwTimeMaxAmount) {
        this.minUwTimeMaxAmount = minUwTimeMaxAmount;
    }

    public LocalTime getMaxUwTimeMaxAmount() {
        return maxUwTimeMaxAmount;
    }

    public void setMaxUwTimeMaxAmount(LocalTime maxUwTimeMaxAmount) {
        this.maxUwTimeMaxAmount = maxUwTimeMaxAmount;
    }

    public int getMaxLoansPerDay() {
        return maxLoansPerDay;
    }

    public void setMaxLoansPerDay(int maxLoansPerDay) {
        this.maxLoansPerDay = maxLoansPerDay;
    }

    public DateTime getDateNow() {
        return dateNow;
    }

    public void setDateNow(DateTime dateNow) {
        this.dateNow = dateNow;
    }

    public BigDecimal getInterestFactor() {
        return interestFactor;
    }

    public void setInterestFactor(BigDecimal interestFactor) {
        this.interestFactor = interestFactor;
    }

    public BigDecimal getExtensionInterestFactor() {
        return extensionInterestFactor;
    }

    public void setExtensionInterestFactor(BigDecimal extensionInterestFactor) {
        this.extensionInterestFactor = extensionInterestFactor;
    }

    public int getExtensionInterestPerPeriodDays() {
        return extensionInterestPerPeriodDays;
    }

    public void setExtensionInterestPerPeriodDays(int extensionInterestPerPeriodDays) {
        this.extensionInterestPerPeriodDays = extensionInterestPerPeriodDays;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public enum Process{
        NEW,
        EXTENDED
    }
}
