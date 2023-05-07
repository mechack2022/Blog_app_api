//package com.fragile.blog_api.controllers;
//
//
//
//import com.fragile.blog_api.entities.EmailDetails;
//import com.fragile.blog_api.services.EmailService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/mail")
//public class EmailController {
//
//   private final EmailService emailService;
//
//    // Sending a simple Email
//    @PostMapping("/sendMail")
//    public String
//    sendMail(@RequestBody EmailDetails details)
//    {
//
//        return emailService.sendSimpleMail(details);
//    }
//
//    // Sending email with attachment
//    @PostMapping("/sendMailWithAttachment")
//    public String sendMailWithAttachment(@RequestBody EmailDetails details)
//    {
//        return emailService.sendMailWithAttachment(details);
//    }
//}

