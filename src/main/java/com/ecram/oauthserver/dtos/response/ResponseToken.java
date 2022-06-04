package com.ecram.oauthserver.dtos.response;

import lombok.Data;

@Data
public class ResponseToken {
    private String access_token;
    private String refresh_token;
    private String token_type;
    private Long expires_in;
    private String scope;
    private String sub;
    private String jti;
    private ResponseForLogin user;
}
