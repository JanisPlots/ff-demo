package com.ff.demo.service.model;


public class LoanResult {
    private boolean accepted;

    public LoanResult(boolean accepted){
        this.accepted = accepted;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
