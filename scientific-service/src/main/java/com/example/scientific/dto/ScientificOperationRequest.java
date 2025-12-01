package com.example.scientific.dto;

public class ScientificOperationRequest {
    private String operation; // POWER, SQRT, FACTORIAL, IS_PRIME
    private double value;
    private Double exponent; // for POWER

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Double getExponent() {
        return exponent;
    }

    public void setExponent(Double exponent) {
        this.exponent = exponent;
    }
}
