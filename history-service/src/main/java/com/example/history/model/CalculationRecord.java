package com.example.history.model;

import java.time.LocalDateTime;

public class CalculationRecord {
    private String type; // BASIC, SCIENTIFIC, STATISTICS
    private String expression;
    private String result;
    private LocalDateTime timestamp = LocalDateTime.now();

    public CalculationRecord() {
    }

    public CalculationRecord(String type, String expression, String result) {
        this.type = type;
        this.expression = expression;
        this.result = result;
        this.timestamp = LocalDateTime.now();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
