package com.ff.demo.rest.model;

public class RestLoanResponse {
    private boolean accepted;

    public RestLoanResponse(boolean accepted){
        this.accepted = accepted;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}


