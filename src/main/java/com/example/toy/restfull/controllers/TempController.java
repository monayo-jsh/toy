package com.example.toy.restfull.controllers;

import com.example.toy.restfull.services.TempService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempController {

    private final TempService tempService;

    public TempController(@Qualifier("tempService") TempService tempService) {
        this.tempService = tempService;
    }

    @GetMapping("/api/temp")
    public ResponseEntity<String> tempMethod() {
        return ResponseEntity.ok(tempService.getTempMessage());
    }

}
