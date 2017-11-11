package de.freedriver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OffersController {

    @GetMapping("/")
    public String offers() {
        return "offers";
    }
}
