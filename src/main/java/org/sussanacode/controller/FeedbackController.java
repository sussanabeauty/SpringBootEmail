package org.sussanacode.controller;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sussanacode.configuation.EmailConfiguration;
import org.sussanacode.entity.Feedback;

import javax.validation.ValidationException;

@RestController
@RequestMapping("/feedback")
@AllArgsConstructor
public class FeedbackController {

    private final EmailConfiguration emailConfig;

    //send a reply to an email
    @PostMapping("/reply")
    public void sendFeedback(@RequestBody Feedback feedback, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            throw new ValidationException("Feedback is not valid!!");
        }

        //create a mail sender;
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.emailConfig.getHost());
        mailSender.setPort(this.emailConfig.getPort());
        mailSender.setUsername(this.emailConfig.getUsername());
        mailSender.setPassword(this.emailConfig.getPassword());


        //create an email instance
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(feedback.getEmail());
        mailMessage.setTo("sussanacode@mail.com");
        mailMessage.setSubject("New reply from " + feedback.getName());
        mailMessage.setText(feedback.getReply());

        //send mail
        mailSender.send(mailMessage);


    }
}
