package com.bilgeadam.controller;

// bilgeadam

import com.bilgeadam.dto.request.BaseRequestDto;
import com.bilgeadam.dto.request.LoginRequestDto;
import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.response.LoginResponseDto;
import com.bilgeadam.dto.response.NewEmployeeResponseDto;
import com.bilgeadam.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.bilgeadam.constant.EndPoints.*;

@RestController
@RequestMapping(API + VERSION + AUTH)
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;

    @PostMapping(REGISTER)
    public ResponseEntity<Boolean> register(@RequestBody @Valid RegisterRequestDto registerRequestDto) {
        return ResponseEntity.ok(authService.doRegister(registerRequestDto));
    }

    @PostMapping(LOGIN)
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(authService.doLogin(loginRequestDto));

    }

    @GetMapping("/newemployee")
    public ResponseEntity<List<NewEmployeeResponseDto>> getAllNewEmployee(BaseRequestDto baseRequestDto) {
        return ResponseEntity.ok(authService.getAllNewEmployee(baseRequestDto));
    }
}
