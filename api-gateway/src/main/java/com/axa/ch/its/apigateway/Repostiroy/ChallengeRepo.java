package com.axa.ch.its.apigateway.Repostiroy;

import com.axa.ch.its.apigateway.Entities.Challenge;
import com.axa.ch.its.apigateway.Entities.DTO.ChallengeRequestDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ChallengeRepo extends CrudRepository<Challenge, Long> {
    List<ChallengeRequestDTO> getAll();

}
