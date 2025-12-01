package com.example.statistics.controller;

import com.example.statistics.dto.StatisticsRequest;
import com.example.statistics.dto.StatisticsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/statistics")
@CrossOrigin("*")
public class StatisticsController {

    @PostMapping("/calculate")
    public ResponseEntity<StatisticsResponse> calculate(@RequestBody StatisticsRequest request) {
        List<Double> values = request.getValues();
        if (values == null || values.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        int n = values.size();
        double sum = 0.0;
        for (Double v : values) {
            sum += v;
        }
        double mean = sum / n;

        Collections.sort(values);
        double median;
        if (n % 2 == 0) {
            median = (values.get(n / 2 - 1) + values.get(n / 2)) / 2.0;
        } else {
            median = values.get(n / 2);
        }

        double varianceSum = 0.0;
        for (Double v : values) {
            varianceSum += Math.pow(v - mean, 2);
        }
        double standardDeviation = Math.sqrt(varianceSum / n);

        return ResponseEntity.ok(new StatisticsResponse(mean, median, standardDeviation));
    }
}
