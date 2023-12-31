package com.b208.dduishu.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
//        config.setAllowedOrigins(List.of("https://j9b302.p.ssafy.io")); // 이 출처로 오는 모든 요청을 허용함.
//         config.setAllowedOrigins(List.of("http://localhost:3000"));
        config.setAllowedOrigins(List.of("http://k9b208.p.ssafy.io"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        config.setAllowedHeaders(List.of("*")); // 허용되는 헤더
        config.setExposedHeaders(List.of("*")); // 노출되는 응답 헤어

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

}
