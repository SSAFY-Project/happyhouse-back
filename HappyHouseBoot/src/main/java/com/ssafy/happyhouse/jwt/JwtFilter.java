package com.ssafy.happyhouse.jwt;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);
    private final JwtTokenProvider jwtTokenProvider;
    
    // jwt 토큰의 인증 정보를 현재 실행중인 스레드 ( Security Context ) 에 저장
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
        String requestURI = ((HttpServletRequest) request).getRequestURI();

        if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            logger.info("Security context에 인증 정보를 저장했습니다, uri: {}", requestURI);
        } else {
            logger.debug("유효한 Jwt 토큰이 없습니다, uri: {}", requestURI);
        }

        chain.doFilter(request, response);
    }

}