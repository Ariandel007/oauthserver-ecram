package com.ecram.oauthserver.dtos.request;

import lombok.Data;

@Data
public class UserCredentials {
    private String username;
    private String password;
    private String grant_type;
}
