package com.yigit.basketballappwithgraphql.controller;

import com.yigit.basketballappwithgraphql.dto.LoginRequestDto;
import com.yigit.basketballappwithgraphql.dto.LoginResponseDto;
import com.yigit.basketballappwithgraphql.service.ımpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

//    @MutationMapping
//    @PreAuthorize("isAnonymous()")
//    public LoginResponseDto login(@Argument LoginRequestDto loginRequestDto) throws Exception {
//        return userService.login(loginRequestDto);
//    }

    @PostMapping(value= "/login")
    public LoginResponseDto loginRest(@RequestBody LoginRequestDto loginRequestDto) throws Exception {
        return userService.login(loginRequestDto);
    }
//
//    @GetMapping(value = "/hello")
//    public String hello() throws Exception {
//        String message = "asdsaasd";
//        return message;
//    }
}
