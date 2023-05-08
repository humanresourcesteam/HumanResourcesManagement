package com.bilgeadam.fallback;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class FallbackController {

    @GetMapping("/fallbackauth")
    public ResponseEntity<String>fallbackauth(){
        return ResponseEntity.ok("Auth Service geçici bir süre devre dışıdır lütfen daha sonra tekrar deneyiniz");
    }

    @GetMapping("/fallbackadmin")
    public ResponseEntity<String>fallbackadmin(){
        return ResponseEntity.ok("Admin Service geçici bir süre devre dışıdır lütfen daha sonra tekrar deneyiniz");
    }

    @GetMapping("/fallbackcompany")
    public ResponseEntity<String>fallbackcompany(){
        return ResponseEntity.ok("Company Service geçici bir süre devre dışıdır lütfen daha sonra tekrar deneyiniz");
    }

    @GetMapping("/fallbackmanager")
    public ResponseEntity<String>fallbackmanager(){
        return ResponseEntity.ok("Manager Service geçici bir süre devre dışıdır lütfen daha sonra tekrar deneyiniz");
    }

    @GetMapping("/fallbackworker")
    public ResponseEntity<String>fallbackworker(){
        return ResponseEntity.ok("Worker Service geçici bir süre devre dışıdır lütfen daha sonra tekrar deneyiniz");
    }


}
