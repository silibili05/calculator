package com.example.gateway.controller;

import com.example.gateway.dto.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class GatewayController {

    private final RestTemplate restTemplate;

    @Value("${services.basic.url:http://localhost:8081/basic}")
    private String basicUrl;

    @Value("${services.scientific.url:http://localhost:8082/scientific}")
    private String scientificUrl;

    @Value("${services.statistics.url:http://localhost:8083/statistics}")
    private String statisticsUrl;

    @Value("${services.history.url:http://localhost:8084/history}")
    private String historyUrl;

    public GatewayController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/basic")
    public ResponseEntity<BasicOperationResponse> basic(@RequestBody BasicOperationRequest request) {
        ResponseEntity<BasicOperationResponse> response =
                restTemplate.postForEntity(basicUrl + "/calculate", request, BasicOperationResponse.class);

        if (response.getBody() != null) {
            String expr = request.getA() + " " + request.getOperation() + " " + request.getB();
            String resStr = String.valueOf(response.getBody().getResult());
            addHistory("BASIC", expr, resStr);
        }

        return response;
    }

    @PostMapping("/scientific")
    public ResponseEntity<ScientificOperationResponse> scientific(@RequestBody ScientificOperationRequest request) {
        ResponseEntity<ScientificOperationResponse> response =
                restTemplate.postForEntity(scientificUrl + "/calculate", request, ScientificOperationResponse.class);

        if (response.getBody() != null) {
            String expr = request.getOperation() + "(" + request.getValue();
            if (request.getExponent() != null) {
                expr += ", " + request.getExponent();
            }
            expr += ")";
            String resStr = response.getBody().getResult() != null
                    ? String.valueOf(response.getBody().getResult())
                    : String.valueOf(response.getBody().getBooleanResult());
            addHistory("SCIENTIFIC", expr, resStr);
        }

        return response;
    }

    @PostMapping("/statistics")
    public ResponseEntity<StatisticsResponse> statistics(@RequestBody StatisticsRequest request) {
        ResponseEntity<StatisticsResponse> response =
                restTemplate.postForEntity(statisticsUrl + "/calculate", request, StatisticsResponse.class);

        if (response.getBody() != null) {
            String expr = "STATS" + request.getValues();
            String resStr = "mean=" + response.getBody().getMean()
                    + ", median=" + response.getBody().getMedian()
                    + ", std=" + response.getBody().getStandardDeviation();
            addHistory("STATISTICS", expr, resStr);
        }

        return response;
    }

    @GetMapping("/history")
    public List<CalculationRecord> history() {
        CalculationRecord[] records =
                restTemplate.getForObject(historyUrl + "/all", CalculationRecord[].class);
        return records != null ? Arrays.asList(records) : java.util.Collections.emptyList();
    }

    @DeleteMapping("/history")
    public void clearHistory() {
        restTemplate.delete(historyUrl + "/clear");
    }

    private void addHistory(String type, String expression, String result) {
        CalculationRecord record = new CalculationRecord(type, expression, result);
        restTemplate.postForEntity(historyUrl + "/add", record, Void.class);
    }
}
