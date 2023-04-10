package com.yigit.basketballappwithgraphql.service.ımpl;

import com.yigit.basketballappwithgraphql.dto.LoginRequestDto;
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

    private final AuthenticationManager authenticationManager;


    public String login(LoginRequestDto loginRequestDto) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new Exception("Incorret username or password", ex);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequestDto.getUsername());

        return jwtUtil.generateToken(userDetails);
    }
}
