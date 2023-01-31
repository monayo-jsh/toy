package com.example.toy.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("temp")
public class tempController {

   @RequestMapping("")
   public ResponseEntity tempMethod() {
       return ResponseEntity.ok("tempMethod Success.");
   }

}
