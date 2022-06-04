package com.ecram.oauthserver.configuration;

import io.jsonwebtoken.SignatureAlgorithm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;

@Configuration
public class GeneralConfiguration {
    @Bean(name = "passwordEncoder")
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean(name = "hmacKey")
    public Key hmacKey(@Value("${jwt-secret}") String secret) {
        return new SecretKeySpec(Base64.getEncoder().encode(secret.getBytes(StandardCharsets.UTF_8)),
                SignatureAlgorithm.HS256.getJcaName());
    }

}
