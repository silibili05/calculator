package com.example.scientific.controller;

import com.example.scientific.dto.ScientificOperationRequest;
import com.example.scientific.dto.ScientificOperationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scientific")
@CrossOrigin("*")
public class ScientificController {

    @PostMapping("/calculate")
    public ResponseEntity<ScientificOperationResponse> calculate(@RequestBody ScientificOperationRequest request) {
        String op = request.getOperation();
        double value = request.getValue();

        switch (op) {
            case "POWER":
                if (request.getExponent() == null) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
                double pow = Math.pow(value, request.getExponent());
                return ResponseEntity.ok(new ScientificOperationResponse(pow));

            case "SQRT":
                if (value < 0) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
                return ResponseEntity.ok(new ScientificOperationResponse(Math.sqrt(value)));

            case "FACTORIAL":
                if (value < 0 || value != Math.floor(value) || value > 20) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
                long n = (long) value;
                long fact = 1;
                for (long i = 2; i <= n; i++) {
                    fact *= i;
                }
                return ResponseEntity.ok(new ScientificOperationResponse((double) fact));

            case "IS_PRIME":
                if (value != Math.floor(value) || value < 2) {
                    return ResponseEntity.ok(new ScientificOperationResponse(false));
                }
                long v = (long) value;
                boolean isPrime = true;
                for (long i = 2; i * i <= v; i++) {
                    if (v % i == 0) {
                        isPrime = false;
                        break;
                    }
                }
                return ResponseEntity.ok(new ScientificOperationResponse(isPrime));

            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
