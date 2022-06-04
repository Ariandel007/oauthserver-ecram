package com.ecram.oauthserver.services;

import com.ecram.oauthserver.dtos.request.UserCredentials;
import com.ecram.oauthserver.dtos.response.ResponseForLogin;
import com.ecram.oauthserver.dtos.response.ResponseToken;

public interface ITokenService {
    ResponseToken authUser(UserCredentials userCredentials);
}
