package com.ecram.oauthserver.controllers;

import com.ecram.oauthserver.dtos.request.UserCredentials;
import com.ecram.oauthserver.dtos.response.ResponseToken;
import com.ecram.oauthserver.services.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("v1/authserver")
public class OauthController {
    private final ITokenService tokenService;

    @Autowired
    public OauthController(ITokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping(path = "/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseToken> generateToken(UserCredentials userCredentials, @RequestHeader Map<String, String> headers){
        return ResponseEntity.ok(tokenService.authUser(userCredentials));
    }
}
