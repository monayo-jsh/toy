package com.example.toy.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String index() {
        return "redirect:login";
    }

    @GetMapping("login")
    public String login() {
        return "login/login";
    }

    @GetMapping("dashboard")
    public String dashboard() {
        return "dashboard/dashboard";
    }

}
