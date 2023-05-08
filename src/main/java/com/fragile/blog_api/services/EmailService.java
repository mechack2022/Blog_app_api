package com.fragile.blog_api.services;


import com.fragile.blog_api.entities.EmailDetails;
import org.springframework.web.multipart.MultipartFile;

// Interface
public interface EmailService {

    // Method
    // To send a simple email
//    String sendSimpleMail(EmailDetails details);

    // Method
    // To send an email with attachment
//    String sendMailWithAttachment(EmailDetails details);

    String sendMail(MultipartFile[] file, String to, String[] cc, String subject, String body);
}