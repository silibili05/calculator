package com.example.basic.dto;

public class BasicOperationRequest {
    private String operation; // ADD, SUBTRACT, MULTIPLY, DIVIDE
    private double a;
    private double b;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }
}
