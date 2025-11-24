package com.axa.ch.its.computeservice.controller;

import com.axa.ch.its.computeservice.model.Calculation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/calculations")
public class CalculationController {

    private final Map<Long, Calculation> store = new ConcurrentHashMap<>();
    private final AtomicLong idSeq = new AtomicLong(1);

    @PostMapping
    public ResponseEntity<Calculation> create(@RequestBody Map<String, String> body) {
        String expr = body.getOrDefault("calculation", "").trim();
        String answer;
        try {
            answer = evaluate(expr);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
        Long id = idSeq.getAndIncrement();
        Calculation c = new Calculation(expr, answer);
        try {
            var idField = Calculation.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(c, id);
        } catch (Exception ignored) {}
        store.put(id, c);
        return ResponseEntity.ok(c);
    }

    @GetMapping
    public ResponseEntity<List<Calculation>> list() {
        return ResponseEntity.ok(new ArrayList<>(store.values()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Calculation> get(@PathVariable Long id) {
        Calculation c = store.get(id);
        return c == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(c);
    }

    private String evaluate(String expr) {
        if (expr.isEmpty()) throw new IllegalArgumentException("Leerer Ausdruck");
        List<String> tokens = tokenize(expr);
        Deque<Double> values = new ArrayDeque<>();
        Deque<Character> ops = new ArrayDeque<>();

        for (String t : tokens) {
            if (isNumber(t)) {
                values.push(Double.parseDouble(t));
            } else if (t.length() == 1 && isOperator(t.charAt(0))) {
                char op = t.charAt(0);
                while (!ops.isEmpty() && precedence(ops.peek()) >= precedence(op)) {
                    applyTop(values, ops.pop());
                }
                ops.push(op);
            } else if ("(".equals(t)) {
                ops.push('(');
            } else if (")".equals(t)) {
                while (!ops.isEmpty() && ops.peek() != '(') {
                    applyTop(values, ops.pop());
                }
                if (ops.isEmpty() || ops.pop() != '(') {
                    throw new IllegalArgumentException("Klammerfehler");
                }
            } else {
                throw new IllegalArgumentException("Ungültiges Token: " + t);
            }
        }
        while (!ops.isEmpty()) {
            char op = ops.pop();
            if (op == '(') throw new IllegalArgumentException("Klammerfehler");
            applyTop(values, op);
        }
        if (values.size() != 1) throw new IllegalArgumentException("Ausdrucksfehler");
        double result = values.pop();
        if (Math.floor(result) == result) {
            return Long.toString((long) result);
        }
        return Double.toString(result);
    }

    private List<String> tokenize(String expr) {
        List<String> out = new ArrayList<>();
        StringBuilder num = new StringBuilder();
        for (char c : expr.replaceAll("\\s+", "").toCharArray()) {
            if (Character.isDigit(c) || c == '.') {
                num.append(c);
            } else {
                if (num.length() > 0) {
                    out.add(num.toString());
                    num.setLength(0);
                }
                if ("()+-*/".indexOf(c) >= 0) {
                    out.add(Character.toString(c));
                } else {
                    throw new IllegalArgumentException("Ungültiges Zeichen: " + c);
                }
            }
        }
        if (num.length() > 0) out.add(num.toString());
        return out;
    }

    private boolean isNumber(String s) {
        return s.matches("\\d+(\\.\\d+)?");
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private int precedence(char c) {
        return (c == '+' || c == '-') ? 1 : (c == '*' || c == '/') ? 2 : 0;
    }

    private void applyTop(Deque<Double> values, char op) {
        if (values.size() < 2) throw new IllegalArgumentException("Operator ohne ausreichende Operanden");
        double b = values.pop();
        double a = values.pop();
        double r;
        switch (op) {
            case '+' -> r = a + b;
            case '-' -> r = a - b;
            case '*' -> r = a * b;
            case '/' -> {
                if (b == 0) throw new IllegalArgumentException("Division durch Null");
                r = a / b;
            }
            default -> throw new IllegalArgumentException("Unbekannter Operator: " + op);
        }
        values.push(r);
    }
}
