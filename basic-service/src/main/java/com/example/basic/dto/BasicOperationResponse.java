package com.example.basic.dto;

public class BasicOperationResponse {
    private double result;

    public BasicOperationResponse() {
    }

    public BasicOperationResponse(double result) {
        this.result = result;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
