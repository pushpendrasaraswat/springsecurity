package com.ps.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

    @GetMapping("/contact")
    String saveCOntactInquiryDetails(){
        return "Inquiry details are saved to DB";
    }
}
