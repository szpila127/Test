package com.crud.tasks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SimpleEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void send(final String receiverEmail, final String subject, final String message) {

    }

}
