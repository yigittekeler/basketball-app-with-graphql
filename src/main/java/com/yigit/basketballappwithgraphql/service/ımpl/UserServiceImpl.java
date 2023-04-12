package com.yigit.basketballappwithgraphql.service.ımpl;

import com.yigit.basketballappwithgraphql.dto.LoginRequestDto;
import com.yigit.basketballappwithgraphql.dto.LoginResponseDto;
import com.yigit.basketballappwithgraphql.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl{

    private final CustomUserDetailsService userDetailsService;

    private final JwtUtil jwtUtil;



    public LoginResponseDto login(LoginRequestDto loginRequestDto) throws Exception {

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequestDto.getUsername());

        return LoginResponseDto.builder().token(jwtUtil.generateToken(userDetails)).build();
    }
}
