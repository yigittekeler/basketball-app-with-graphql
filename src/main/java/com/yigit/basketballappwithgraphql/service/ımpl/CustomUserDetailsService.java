package com.yigit.basketballappwithgraphql.service.ımpl;

import com.yigit.basketballappwithgraphql.entity.User;
import com.yigit.basketballappwithgraphql.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null ) {
            throw new UsernameNotFoundException("user not found");
        }
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),roles);
    }
}