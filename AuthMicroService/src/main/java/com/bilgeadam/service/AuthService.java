package com.bilgeadam.service;

import com.bilgeadam.dto.request.LoginRequestDto;
import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.exception.AuthException;
import com.bilgeadam.exception.EErrorType;
import com.bilgeadam.mapper.IAuthMapper;
import com.bilgeadam.rabbitmq.model.CreateModel;
import com.bilgeadam.rabbitmq.producer.AuthProducer;
import com.bilgeadam.repository.IAuthRepository;
import com.bilgeadam.repository.entity.Auth;
import com.bilgeadam.utility.JwtTokenManager;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth,Long> {

    private final IAuthRepository repository;

    private final JwtTokenManager jwtTokenManager;

    private final AuthProducer authProducer;

    public AuthService(IAuthRepository repository, JwtTokenManager jwtTokenManager, AuthProducer authProducer){
        super(repository);
        this.repository=repository;
        this.jwtTokenManager = jwtTokenManager;
        this.authProducer = authProducer;
    }

    public Boolean doRegister(RegisterRequestDto registerRequestDto) {
        if (!registerRequestDto.getPassword().equals(registerRequestDto.getRepassword()))
            throw new AuthException(EErrorType.AUTH_PASSWORD_ERROR);
        if (repository.existsByEmail(registerRequestDto.getEmail())) throw new AuthException(EErrorType.AUTH_EMAIL_ERROR);
        Auth auth = save(IAuthMapper.INSTANCE.fromRegisterDto(registerRequestDto));
        authProducer.createAdmin(CreateModel.builder()
                        .authid(auth.getId())
                        .email(auth.getEmail())
                .build());
        // KAYIT DİĞER MİCROSERVİCELERE İLETİLECEK...
        return true;
    }

    public String doLogin(LoginRequestDto loginRequestDto) {
        Optional<Auth>auth = repository.findOptionalByEmailAndPassword(loginRequestDto.getEmail(),loginRequestDto.getPassword());
        if (auth.isEmpty()) throw new AuthException(EErrorType.AUTH_LOGIN_ERROR);
        Optional<String>token = jwtTokenManager.createToken(auth.get().getId());
        if (token.isEmpty()) throw new AuthException(EErrorType.INVALID_TOKEN);
        return token.get();
    }
}
