package com.fragile.blog_api.controllers;


import com.fragile.blog_api.entities.EmailDetails;
import com.fragile.blog_api.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/sendMail")
    public String sendMail(@RequestParam(value = "file", required = false)MultipartFile[] file, String to, String[] cc, String subject, String body){
        return emailService.sendMail(file, to, cc, subject, body);
    }
}

