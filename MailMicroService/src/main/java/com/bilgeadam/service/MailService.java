package com.bilgeadam.service;

import com.bilgeadam.rabbitmq.model.MailManagerPassword;
import com.bilgeadam.repository.MailRepository;
import com.bilgeadam.repository.entity.Mail;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class MailService extends ServiceManager<Mail, Long> {

    private final MailRepository mailRepository;
    private final EmailSenderService emailSenderService;

    public MailService(MailRepository mailRepository, EmailSenderService emailSenderService) {
        super(mailRepository);
        this.mailRepository = mailRepository;
        this.emailSenderService = emailSenderService;
    }


    public void sendPassword(MailManagerPassword mailManagerPassword) {
        Mail mail = Mail.builder()
                .email(mailManagerPassword.getMail())
                .authid(mailManagerPassword.getAuthid())
                .build();
        save(mail);
        //save attıktan sonra ilk passwordu kayıt olan email gönderme
        emailSenderService.sendEmail(mail.getEmail(), "FIRST PASSWORD", mailManagerPassword.getPassword());
    }
}
