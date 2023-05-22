package com.bilgeadam.service;


import com.bilgeadam.dto.request.LoginRequestDto;
import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.response.LoginResponseDto;
import com.bilgeadam.exception.AuthException;
import com.bilgeadam.rabbitmq.model.*;
import com.bilgeadam.rabbitmq.producer.AuthProducer;
import com.bilgeadam.repository.IAuthRepository;
import com.bilgeadam.repository.entity.Auth;
import com.bilgeadam.repository.enums.ERole;
import com.bilgeadam.utility.JwtTokenManager;
import com.bilgeadam.utility.ServiceManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static com.bilgeadam.repository.enums.ERole.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthServiceTest {

    @Mock
    private IAuthRepository repository;

    @Mock
    private JwtTokenManager jwtTokenManager;

    @Mock
    private AuthProducer authProducer;
    @InjectMocks
    private AuthService authService;

    @InjectMocks
    private ServiceManager serviceManager;
    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDoLogin() {
        // Giriş bilgileri
        String email = "melihcan@gmail.com";
        String password = "3462";
        // Auth nesnesi
        Auth auth = new Auth();
        auth.setId(1L);
        auth.setRoles(ERole.ADMIN);
        // Mock repository
        Mockito.when(repository.findOptionalByEmailAndPassword(email, password)).thenReturn(Optional.of(auth));
        // JWT tokeni
        String token = "your_generated_token";
        // Mock jwtTokenManager
        Mockito.when(jwtTokenManager.createToken(auth.getId())).thenReturn(Optional.of(token));
        // LoginRequestDto nesnesini oluşturun
        LoginRequestDto loginRequestDto = new LoginRequestDto(email, password);
        // Metodu çağırın ve LoginResponseDto nesnesini alın
        LoginResponseDto loginResponseDto = authService.doLogin(loginRequestDto);
        // Beklenen LoginResponseDto nesnesi
        LoginResponseDto expectedResponseDto = new LoginResponseDto();
        expectedResponseDto.setERole(ERole.ADMIN);
        expectedResponseDto.setToken(token);
        // Sonuçları kontrol edin
        assertNotNull(loginResponseDto);
        assertEquals(expectedResponseDto.getERole(), loginResponseDto.getERole());
        assertEquals(expectedResponseDto.getToken(), loginResponseDto.getToken());
    }

    @Test
    @DisplayName("Update Auth")
    void testUpdateAuth(){
        String email = "test@example.com";
        long authId = 1234L;

        Auth auth = new Auth();
        auth.setId(authId);
        auth.setEmail(email);
        when(repository.findOptionalByEmail(email)).thenReturn(Optional.empty());
        when(repository.findOptionalById(authId)).thenReturn(Optional.of(auth));

        UpdateAuthModel authModel = new UpdateAuthModel();
        authModel.setEmail(email);
        authModel.setAuthid(authId);
        boolean result = authService.updateAuth(authModel);

        assertTrue(result);
        verify(repository,times(1)).save(auth);
    }

    @Test
    @DisplayName("Fail Update Auth")
    void testUpdateAuth_Failure(){
        String email = "test@example.com";
        long authId = 1234L;
        when(repository.findOptionalByEmail(email)).thenReturn(Optional.of(new Auth()));

        UpdateAuthModel authModel = new UpdateAuthModel();
        authModel.setEmail(email);
        authModel.setAuthid(authId);
        boolean result = authService.updateAuth(authModel);

        assertFalse(result);
        verify(repository,never()).findOptionalById(anyLong());
        verify(repository,never()).save(any());
    }


    @Test
    @DisplayName("Register Success")
    void testDoRegister_Success(){
        String email = "aliogutcen@gmail.com";
        String password= "password";
        String repassword = "password";

        when(repository.findOptionalByEmail(email)).thenReturn(Optional.empty());
        when(repository.save(any(Auth.class))).thenReturn(new Auth());


        RegisterRequestDto registerRequestDto = new RegisterRequestDto();
        registerRequestDto.setEmail(email);
        registerRequestDto.setPassword(password);
        registerRequestDto.setRepassword(repassword);

        boolean result = authService.doRegister(registerRequestDto);

        assertTrue(result);
        verify(authProducer,times(1)).createAdmin(any(CreateModel.class));
    }

    @Test
    @DisplayName("Failed Registration due to password")
    void testDoRegister_FailurePassword(){
        String email = "test@example.com";
        String password = "password";
        String repassword = "wrongpass";

        when(repository.findOptionalByEmail(email)).thenReturn(Optional.of(new Auth()));

        RegisterRequestDto registerRequestDto = new RegisterRequestDto();
        registerRequestDto.setEmail(email);
        registerRequestDto.setPassword(password);
        registerRequestDto.setRepassword(repassword);

        assertThrows(AuthException.class,()->authService.doRegister(registerRequestDto));
        verify(authProducer,never()).createAdmin(any(CreateModel.class));
    }


    @Test
    @DisplayName("Failed registration due to email")
    void testDoRegister_FailureEmail(){
        String email = "testexample.com";
        String password = "password";
        String repassword = "password";

        when(repository.findOptionalByEmail(email)).thenReturn(Optional.of(new Auth()));

        RegisterRequestDto registerRequestDto = new RegisterRequestDto();
        registerRequestDto.setEmail(email);
        registerRequestDto.setPassword(password);
        registerRequestDto.setRepassword(repassword);

        assertThrows(AuthException.class,()->authService.doRegister(registerRequestDto));
        verify(authProducer,never()).createAdmin(any(CreateModel.class));
    }


    @Test
    public void testCreateManager_Failure() {

        CreateManager createManager = new CreateManager("manager@example.com");
        Auth existingAuth = new Auth(1L, "existing@example.com", "password123",MANAGER );
        when(repository.findOptionalByEmail(createManager.getEmail())).thenReturn(Optional.of(existingAuth));


        Long result = authService.createManager(createManager);
        System.out.println(result);
        // Assert
        assertEquals(0L, result);
        verify(repository, never()).save(any(Auth.class));
        verify(authProducer, never()).sendPasswordAfterManagerCreate(any(MailManagerPassword.class));

    }

    @Test
    void testCreateManager(){
        CreateManager createManager = new CreateManager("manager@example.com");
        Auth existingAuth = new Auth(1L, "existing@example.com", "password123",MANAGER );

        Long result = authService.createManager(createManager);
        assertNotEquals(0L, result);
        verify(repository, times(1)).save(any(Auth.class));
        verify(authProducer, times(1)).sendPasswordAfterManagerCreate(any(MailManagerPassword.class));
    }

    @Test
    public void testCreateWorker_Failure(){
        CreateWorker createWorker = new CreateWorker("worker@gmail.com");
        Auth existingAuth = new Auth(1L,"existing@worker.com","password123", EMPLOYEE);
        when(repository.findOptionalByEmail(createWorker.getEmail())).thenReturn(Optional.of(existingAuth));
        Long result = authService.createWorker(createWorker);
        assertEquals(0L,result);
         }

    @Test
    void testCreateWorker(){
        CreateWorker createWorker = new CreateWorker("manager@example.com");
        Auth existingAuth = new Auth();
        existingAuth.setEmail("existing@example.com");
        existingAuth.setPassword("password123");
        Long result = authService.createWorker(createWorker);
        assertNotEquals(0L,result);

    }

    @Test
    @DisplayName("Forgot password")
    void testForgotPassword(){
        PasswordForgot passwordForgot = new PasswordForgot();
        passwordForgot.setAuthid(1L);
        passwordForgot.setEmail("auth@example.com");
        String newPassword = "newPassword";
        Auth auth = new Auth();
        auth.setId(1L);
        auth.setPassword("oldPassword");
        auth.setEmail("auth@example.com");
        when(repository.findById(passwordForgot.getAuthid())).thenReturn(Optional.of(auth));
        String result = authService.forgotPassword(passwordForgot);
        assertNotNull(result,"yanlış geldi");


    }




}
