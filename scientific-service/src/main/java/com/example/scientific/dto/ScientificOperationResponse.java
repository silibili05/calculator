package com.example.scientific.dto;

public class ScientificOperationResponse {
    private Double result;
    private Boolean booleanResult;

    public ScientificOperationResponse() {
    }

    public ScientificOperationResponse(Double result) {
        this.result = result;
    }

    public ScientificOperationResponse(Boolean booleanResult) {
        this.booleanResult = booleanResult;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public Boolean getBooleanResult() {
        return booleanResult;
    }

    public void setBooleanResult(Boolean booleanResult) {
        this.booleanResult = booleanResult;
    }
}
