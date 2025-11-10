package com.axa.ch.its.apigateway.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Challenge {

    @Id
    private Long id;
    private String question;
    private String solution;



}
