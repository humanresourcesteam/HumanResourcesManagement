package com.bilgeadam.service;

import com.bilgeadam.dto.request.BaseRequestDto;
import com.bilgeadam.dto.request.LoginRequestDto;
import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.response.NewEmployeeResponseDto;
import com.bilgeadam.exception.AuthException;
import com.bilgeadam.exception.EErrorType;
import com.bilgeadam.mapper.IAuthMapper;
import com.bilgeadam.rabbitmq.model.*;
import com.bilgeadam.rabbitmq.producer.AuthProducer;
import com.bilgeadam.repository.IAuthRepository;
import com.bilgeadam.repository.entity.Auth;
import com.bilgeadam.repository.enums.ERole;
import com.bilgeadam.utility.JwtTokenManager;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService extends ServiceManager<Auth, Long> {

    private final IAuthRepository repository;

    private final JwtTokenManager jwtTokenManager;

    private final AuthProducer authProducer;


    public AuthService(IAuthRepository repository, JwtTokenManager jwtTokenManager, AuthProducer authProducer) {
        super(repository);
        this.repository = repository;
        this.jwtTokenManager = jwtTokenManager;
        this.authProducer = authProducer;

    }

    public Boolean doRegister(RegisterRequestDto registerRequestDto) {
        if (!registerRequestDto.getPassword().equals(registerRequestDto.getRepassword()))
            throw new AuthException(EErrorType.AUTH_PASSWORD_ERROR);
        if (repository.findOptionalByEmail(registerRequestDto.getEmail()).isPresent())
            throw new AuthException(EErrorType.AUTH_EMAIL_ERROR);
        Auth auth = save(IAuthMapper.INSTANCE.fromRegisterDto(registerRequestDto));
        authProducer.createAdmin(CreateModel.builder()
                .authid(auth.getId())
                .email(auth.getEmail())
                .build());
        // KAYIT DİĞER MİCROSERVİCELERE İLETİLECEK...
        return true;
    }

    public String doLogin(LoginRequestDto loginRequestDto) {
        Optional<Auth> auth = repository.findOptionalByEmailAndPassword(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        if (auth.isEmpty()) throw new AuthException(EErrorType.AUTH_LOGIN_ERROR);
        Optional<String> token = jwtTokenManager.createToken(auth.get().getId());
        if (token.isEmpty()) throw new AuthException(EErrorType.INVALID_TOKEN);
        return token.get();
    }

    public boolean updateAuth(UpdateAuthModel updateAuthModel) {
        Optional<Auth> authOptional = repository.findOptionalByEmail(updateAuthModel.getEmail());
        if (authOptional.isEmpty()) {
            Optional<Auth> auth = repository.findOptionalById(updateAuthModel.getAuthid());
            auth.get().setId(updateAuthModel.getAuthid());
            auth.get().setEmail(updateAuthModel.getEmail());
            update(auth.get());
            return true;
        }
        return false;
    }

    public List<NewEmployeeResponseDto> getAllNewEmployee(BaseRequestDto baseRequestDto) {
        List<NewEmployeeResponseDto> newEmployeeResponseDtos = new ArrayList<>();
        repository.findTop5ByOrderByCreatedateDesc().forEach(x -> {
            newEmployeeResponseDtos.add(NewEmployeeResponseDto.builder()
                    .id(x.getId())
                    .email(x.getEmail())
                    .build());
        });

        return newEmployeeResponseDtos;

    }

    public Long createManager(CreateManager createManager) {
        Optional<Auth> auth = repository.findOptionalByEmail(createManager.getEmail());
        if (auth.isEmpty()) {
            Auth authManager = Auth.builder()
                    .roles(ERole.MANAGER)
                    .password(UUID.randomUUID().toString())
                    .email(createManager.getEmail())
                    .build();
            save(authManager);
            authProducer.sendPasswordAfterManagerCreate(MailManagerPassword.builder()
                    .mail(authManager.getEmail())
                    .authid(authManager.getId())
                    .password(authManager.getPassword())
                    .build());
            return authManager.getId();
        }
        return 0L;

    }

    public Long createWorker(CreateWorker createWorker) {
        Optional<Auth> auth = repository.findOptionalByEmail(createWorker.getEmail());
        if (auth.isEmpty()) {
            Auth authWorker = Auth.builder()
                    .email(createWorker.getEmail())
                    .password(UUID.randomUUID().toString())
                    .roles(ERole.EMPLOYEE)
                    .build();
            save(authWorker);
            authProducer.sendPasswordAfterManagerCreate(MailManagerPassword.builder()
                    .mail(authWorker.getEmail())
                    .authid(authWorker.getId())
                    .password(authWorker.getPassword())
                    .build());
            return authWorker.getId();
        }
        return 0L;
    }
}
