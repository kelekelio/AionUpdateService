package com.aionpowerbook.aionupdateservice.main.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class MainController {

    @GetMapping("/")
    public String index() {
        return "hello";
    }
}
