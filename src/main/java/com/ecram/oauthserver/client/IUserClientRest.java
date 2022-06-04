package com.ecram.oauthserver.client;

import com.ecram.oauthserver.dtos.response.UserApplicationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="user-service-ecram", path = "/v1/users")
public interface IUserClientRest {

    @GetMapping("/findUser/{username}")
    ResponseEntity<UserApplicationDto> getUserByUsername(@PathVariable String username);
}
