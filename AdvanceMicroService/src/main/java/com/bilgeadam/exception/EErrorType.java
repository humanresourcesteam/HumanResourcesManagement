package com.bilgeadam.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum EErrorType {

    BAD_REQUEST_ERROR(1201,"Geçersiz Parametre Girişi Yaptınız",BAD_REQUEST),
    AUTH_PASSWORD_ERROR(1301,"Şifreler uyuşmuyor.",BAD_REQUEST),

    //  AUTH_EMAIL_ERROR(1302,"Bu email daha önceden kullanılmıştır.",BAD_REQUEST), İÇERİĞİ DEĞİŞTİRİLECEK EMAIL KULLANILMISTIR HATASI DÖNÜLMEYECEK.
    AUTH_EMAIL_ERROR(1302,"Bu email daha önceden kullanılmıştır.",BAD_REQUEST),
    INTERNAL_ERROR(3000,"Sunucuda beklenmeyen hata",INTERNAL_SERVER_ERROR),
    KULLANICI_BULUNAMADI(2301,"Aradığınız id ye ait kullanıcı bulunamamıştır",INTERNAL_SERVER_ERROR),

    INVALID_TOKEN(4001,"GEÇERSİZ TOKEN BİLGİSİ",BAD_REQUEST),
    AUTH_LOGIN_ERROR(4002,"KULLANICI ADI YA DA ŞİFRE HATALIDIR." ,INTERNAL_SERVER_ERROR ),

    MANAGER_HAS_BEEN_WITH_COMPANY(4005,"Bu şirkete kayıtlı menajer vardır",BAD_REQUEST),

    ADVANCE_HAS_BEEN(4006,"ADVANCE_HAS_BEEN",INTERNAL_SERVER_ERROR),

    ;

    private int code;

    private String message;

    private HttpStatus httpStatus;
}
