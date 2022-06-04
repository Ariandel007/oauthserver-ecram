package com.ecram.oauthserver.dtos.response;

import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class UserApplicationDto {
    private Long id;
    private String username;
    private String password;
    private String email;
    private boolean isDeleted = false;
    private boolean isBlocked = false;
    private String authType = "email_registered";
    private short attemps = 0;
    private String firstname;
    private String lastname;
    private String country;
    private String city;
    private String age;
    private Instant birthDate;
    private String birthDateUtc;
    private Instant accountCreationDate;
    private String accountCreationDateUtc;
    private List<RolAppDto> rolAppList;
    private String imageUrl;
//    private List<Email> emailList;
}
