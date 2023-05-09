package com.bilgeadam.controller;

import com.bilgeadam.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bilgeadam.constant.EndPoints.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(API + VERSION + MAIL)
public class MailController {

    private final MailService mailService;
}
