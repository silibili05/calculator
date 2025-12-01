package com.example.basic.controller;

import com.example.basic.dto.BasicOperationRequest;
import com.example.basic.dto.BasicOperationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/basic")
@CrossOrigin("*")
public class BasicController {

    @PostMapping("/calculate")
    public ResponseEntity<BasicOperationResponse> calculate(@RequestBody BasicOperationRequest request) {
        double a = request.getA();
        double b = request.getB();
        String op = request.getOperation();

        double result;
        switch (op) {
            case "ADD":
                result = a + b;
                break;
            case "SUBTRACT":
                result = a - b;
                break;
            case "MULTIPLY":
                result = a * b;
                break;
            case "DIVIDE":
                if (b == 0) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
                result = a / b;
                break;
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok(new BasicOperationResponse(result));
    }
}
