package com.ecram.oauthserver.services.impl;

import com.ecram.oauthserver.client.IUserClientRest;
import com.ecram.oauthserver.dtos.request.UserCredentials;
import com.ecram.oauthserver.dtos.response.ResponseForLogin;
import com.ecram.oauthserver.dtos.response.ResponseToken;
import com.ecram.oauthserver.dtos.response.UserApplicationDto;
import com.ecram.oauthserver.services.ITokenService;
import io.jsonwebtoken.Jwts;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Service
public class TokenService implements ITokenService {

    private final IUserClientRest userClientRest;
    private final PasswordEncoder passwordEncoder;
    private final Key hmacKey;
    private final ModelMapper modelMapper;

    @Autowired
    public TokenService(IUserClientRest userClientRest, PasswordEncoder passwordEncoder, Key hmacKey, ModelMapper modelMapper) {
        this.userClientRest = userClientRest;
        this.passwordEncoder = passwordEncoder;
        this.hmacKey = hmacKey;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseToken authUser(UserCredentials userCredentials) {
        String username = userCredentials.getUsername();
        UserApplicationDto userInBd = userClientRest.getUserByUsername(username).getBody();

        if(this.passwordEncoder.matches(userCredentials.getPassword(),userInBd.getPassword())){
            Instant now = Instant.now();
            String idJwt = UUID.randomUUID().toString();
            String accessToken = Jwts.builder()
                    .claim("username", userInBd.getUsername())
                    .claim("email", userInBd.getEmail())
                    .setSubject(userInBd.getId().toString())
                    .setId(idJwt)
                    .setIssuedAt(Date.from(now))
                    .setExpiration(Date.from(now.plus(5, ChronoUnit.MINUTES)))
                    .signWith(hmacKey)
                    .compact();
            ResponseToken responseToken = new ResponseToken();
            responseToken.setSub(userInBd.getId().toString());
            responseToken.setToken_type("bearer");
            responseToken.setExpires_in(1L);
            responseToken.setScope("read write");
            responseToken.setJti(idJwt);
            responseToken.setAccess_token(accessToken);

            responseToken.setUser(this.modelMapper.map(userInBd, ResponseForLogin.class));

            return responseToken;
        }
        return null;
    }
}
