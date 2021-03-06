package com.kodilla.newideas.service;

import com.kodilla.newideas.config.AdminConfig;
import com.kodilla.newideas.domain.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    DbService dbService;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    SimpleEmailService emailService;

    @Autowired
    private AdminConfig adminConfig;

    @Scheduled(fixedDelay = 10)
    public void sendNumberOfIdeas(){
        String size = dbService.countIdeas();
        emailService.send(new Mail(
                adminConfig.getAdminMail(),
                "SUBJECT",
                "In database is "+size + " ideas"));
    }

    //Przekopiuj kod z EmailShelduera


}
