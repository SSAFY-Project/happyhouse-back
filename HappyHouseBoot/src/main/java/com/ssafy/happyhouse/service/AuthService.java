package com.ssafy.happyhouse.service;

import java.util.Collections;
import java.util.Map;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.dao.AuthMapper;
import com.ssafy.happyhouse.dto.LoginDto;
import com.ssafy.happyhouse.dto.UserDto;
import com.ssafy.happyhouse.exception.DuplicatedUsernameException;
import com.ssafy.happyhouse.exception.LoginFailedException;
import com.ssafy.happyhouse.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {
	
	private final AuthMapper authMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    
    public String login(LoginDto loginDto) {
		UserDto userDto = authMapper.findByUserId(loginDto.getUsername())
                .orElseThrow(() -> new LoginFailedException("잘못된 아이디입니다"));

        if (!passwordEncoder.matches(loginDto.getPassword(), userDto.getPassword())) {
            throw new LoginFailedException("잘못된 비밀번호입니다");
        }

        return jwtTokenProvider.createToken(userDto.getUsername(), Collections.singletonList(userDto.getUserRole()));
	}
    
    @Transactional
    public UserDto register(UserDto userDto) {
        if (authMapper.findByUserId(userDto.getUsername()).isPresent()) {
            throw new DuplicatedUsernameException("이미 가입된 유저입니다");
        }

        userDto.setUserPwd(passwordEncoder.encode(userDto.getPassword()));
        authMapper.registerUser(userDto);

        return authMapper.findByUserId(userDto.getUsername()).get();
    }
    
    
}
