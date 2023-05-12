package com.bilgeadam;

import io.netty.handler.codec.http.HttpMethod;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class GlobalCorsConfig {
    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOriginPattern("*");
        corsConfiguration.addAllowedMethod(String.valueOf(HttpMethod.GET));
        corsConfiguration.addAllowedMethod(String.valueOf(HttpMethod.POST));
        corsConfiguration.addAllowedMethod(String.valueOf(HttpMethod.PUT));
        corsConfiguration.addAllowedMethod(String.valueOf(HttpMethod.DELETE));
        corsConfiguration.addAllowedMethod(String.valueOf(HttpMethod.PATCH));
        corsConfiguration.addAllowedMethod(String.valueOf(HttpMethod.OPTIONS));
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsWebFilter(source);
    }
}
