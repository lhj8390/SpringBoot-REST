package com.lhj8390.dashboard.auth;

import com.lhj8390.dashboard.model.User;
import com.lhj8390.dashboard.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("해당 이름의 사용자가 없습니다."));

        return new org.springframework.security.core.userdetails.User
                (user.getUsername(), user.getPassword(), Collections.emptyList());
    }
}
