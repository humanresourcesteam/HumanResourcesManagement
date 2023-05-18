package com.bilgeadam.service;

import com.bilgeadam.dto.request.EmailRequestDto;
import com.bilgeadam.rabbitmq.model.MailManagerPassword;
import com.bilgeadam.rabbitmq.model.PasswordForgot;
import com.bilgeadam.rabbitmq.producer.MailProducer;
import com.bilgeadam.repository.MailRepository;
import com.bilgeadam.repository.entity.Mail;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MailService extends ServiceManager<Mail, Long> {

    private final MailRepository mailRepository;
    private final EmailSenderService emailSenderService;

    private final MailProducer mailProducer;

    public MailService(MailRepository mailRepository, EmailSenderService emailSenderService, MailProducer mailProducer) {
        super(mailRepository);
        this.mailRepository = mailRepository;
        this.emailSenderService = emailSenderService;
        this.mailProducer = mailProducer;
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

    public Boolean forgotPassword(EmailRequestDto emailRequestDto) {

        Optional<Mail> mail = mailRepository.findOptionalByEmail(emailRequestDto.getEmail());
        if (mail.isEmpty()) return false;

        String newPassword = mailProducer.forgotPassword(PasswordForgot.builder()
                .authid(mail.get().getAuthid())
                .email(mail.get().getEmail())
                .build());

        emailSenderService.sendEmail(mail.get().getEmail(), "NEW PASSWORD", newPassword);
        return true;
    }
}
