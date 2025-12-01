package com.example.history.controller;

import com.example.history.model.CalculationRecord;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/history")
@CrossOrigin("*")
public class HistoryController {

    private final List<CalculationRecord> records = Collections.synchronizedList(new ArrayList<>());

    @PostMapping("/add")
    public void add(@RequestBody CalculationRecord record) {
        records.add(record);
    }

    @GetMapping("/all")
    public List<CalculationRecord> all() {
        return records;
    }

    @DeleteMapping("/clear")
    public void clear() {
        records.clear();
    }
}
