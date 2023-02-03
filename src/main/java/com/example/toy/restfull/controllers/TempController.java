package com.example.toy.restfull.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TempController {

    @GetMapping("/api/temp")
    public ResponseEntity<String> tempMethod() {
        return ResponseEntity.ok("tempMethod Success");
    }
}
