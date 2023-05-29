package com.bilgeadam.controller;

import com.bilgeadam.dto.request.EmailRequestDto;
import com.bilgeadam.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bilgeadam.constant.EndPoints.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(API + VERSION + MAIL)
@CrossOrigin("*")
public class MailController {

    private final MailService mailService;

    @PostMapping("/forgot")
    public ResponseEntity<?> forgotPassword (@RequestBody EmailRequestDto emailRequestDto){
        return ResponseEntity.ok(mailService.forgotPassword(emailRequestDto));
    }
}
