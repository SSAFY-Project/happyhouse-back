package com.ssafy.happyhouse.service;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.AuthMapper;
import com.ssafy.happyhouse.dao.UserMapper;
import com.ssafy.happyhouse.dto.UserDto;

import java.util.Arrays;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final AuthMapper authMapper;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return authMapper.findByUserId(userId)
        		.map(user -> addAuthorities(user))
                .orElseThrow(() -> new UsernameNotFoundException(userId + "> 찾을 수 없습니다."));
    }
    
    private UserDto addAuthorities(UserDto userDto) {
        userDto.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(userDto.getUserRole())));

        return userDto;
    }
}
