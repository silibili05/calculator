package com.axa.ch.its.computeservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Calculation {

    @Id
    @GeneratedValue
    private Long id;

    private String calculation;
    private String answer;

    public Calculation() {
    }

    public Calculation(String calculation, String answer) {
        this.calculation = calculation;
        this.answer = answer;
    }

    public Long getId() {
        return id;
    }

    public String getCalculation() {
        return calculation;
    }

    public void setCalculation(String calculation) {
        this.calculation = calculation;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
