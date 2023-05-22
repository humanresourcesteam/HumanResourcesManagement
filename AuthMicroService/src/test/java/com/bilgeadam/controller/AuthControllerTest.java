package com.bilgeadam.controller;


import com.bilgeadam.service.AuthService;
import com.bilgeadam.utility.JwtTokenManager;
import okhttp3.*;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthControllerTest {

    @InjectMocks
    AuthService authService;
    @Mock
    JwtTokenManager jwtTokenManager;

    @Test
    void loginTest() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(mediaType,"{\n" +
                "    \"email\": \"melihcannozturkk@gmail.com\",\n" +
                "    \"password\": \"123\" " +
                "}");
        Request request = new Request.Builder()
                .addHeader("Content-Type","application/json")
                .url("http://localhost:9090/api/v1/auth/login")
                .method("POST",requestBody)
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
        System.out.println(request);

    }

    @Test
    void loginTest_InvalidCredentials() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(mediaType, "{\n" +
                "    \"email\": \"melihcan@gmail.com\",\n" +
                "    \"password\": \"wrongpassword\",\n" +
                "}");
        Request request = new Request.Builder()
                .addHeader("Content-Type", "application/json")
                .url("http://localhost:9090/api/v1/auth/login")
                .method("POST", requestBody)
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());

        // Hata durumunu kontrol etmek için aşağıdaki gibi bir kod ekleyebilirsiniz.
        assertEquals(400, response.code()); // Beklenen hata durumu kodu
    }



//    @Mock
//    private AuthService authService;
//
//    @InjectMocks
//    private AuthController registrationController;
//
//    private Validator validator;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//
//        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
//        validator = validatorFactory.getValidator();
//    }
//
//    @Test
//    public void testRegister_SuccessfulRegistration() {
//        // Arrange
//        RegisterRequestDto registerRequestDto = new RegisterRequestDto();
//        registerRequestDto.setEmail("test@example.com");
//        registerRequestDto.setPassword("password");
//        registerRequestDto.setRepassword("password");
//        BindingResult bindingResult = Mockito.mock(BindingResult.class);
//        Mockito.when(authService.doRegister(registerRequestDto)).thenReturn(true);
//
//        // Act
//        ResponseEntity<?> response = registrationController.register(registerRequestDto,bindingResult);
//
//        // Assert
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        System.out.println(response.getStatusCode());
//        assertTrue(response.getBody().equals(true));
//        Mockito.verify(authService, Mockito.times(1)).doRegister(registerRequestDto);
//    }
//
//    @Test
//    public void testRegister_InvalidRequest() {
//        // Arrange
//        RegisterRequestDto registerRequestDto = new RegisterRequestDto();
//        registerRequestDto.setEmail("invalid_email");
//        registerRequestDto.setPassword("password");
//        registerRequestDto.setRepassword("password");
//
//        BindingResult bindingResult = Mockito.mock(BindingResult.class);
//        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
//
//        ResponseEntity<?> expectedResponse = ResponseEntity.badRequest().build();
//
//        // Act
//        ResponseEntity<?> response = registrationController.register(registerRequestDto,bindingResult);
//
//        System.out.println(response);
//        // Assert
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        Mockito.verify(authService, Mockito.never()).doRegister(Mockito.any(RegisterRequestDto.class));
//    }
//
//    @Test
//    public void testLogin_SuccessfulLogin() {
//        // Arrange
//        LoginRequestDto loginRequestDto = new LoginRequestDto();
//        loginRequestDto.setEmail("test@example.com");
//        loginRequestDto.setPassword("password");
//
//
//        LoginResponseDto expectedResponseDto = new LoginResponseDto();
//        expectedResponseDto.setToken("token123");
//        expectedResponseDto.setERole(ERole.ADMIN);
//
//
//        Mockito.when(authService.doLogin(loginRequestDto)).thenReturn(expectedResponseDto);
//
//        // Act
//        ResponseEntity<LoginResponseDto> response = registrationController.login(loginRequestDto);
//        System.out.println(response);
//        // Assert
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertEquals(expectedResponseDto.getToken(), response.getBody().getToken());
//        Mockito.verify(authService, Mockito.times(1)).doLogin(loginRequestDto);
//    }



}