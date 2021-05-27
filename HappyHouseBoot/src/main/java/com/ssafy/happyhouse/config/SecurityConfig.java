package com.ssafy.happyhouse.config;

import org.aspectj.weaver.ast.And;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ssafy.happyhouse.jwt.JwtAccessDeniedHandler;
import com.ssafy.happyhouse.jwt.JwtAuthenticationEntryPoint;
import com.ssafy.happyhouse.jwt.JwtFilter;
import com.ssafy.happyhouse.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

// 기본적인 웹보안 활성화
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

	// 기본적인 security 설정
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.httpBasic().disable() // 기본 설정 사용 안함
			.csrf().disable() // csrf 사용 안함
        
			// 오류 처리
             .exceptionHandling()
             .accessDeniedHandler(new JwtAccessDeniedHandler())
             .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
             
	        // 세션 설정
	        .and()
	        .sessionManagement()
	        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 사용 안함
			
	        // 어떤 url에 대해 권한 체크해줄건지 - 순서 상관있음
	        .and()
	        .authorizeRequests() // 다음 리퀘스트에 대한 사용권한 체크하겠다
	        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll() //OPTIONS 메소드 허락
	        .antMatchers(HttpMethod.GET, "/**").permitAll() // GET 요청은 TOKEN없이 접근 가능
			.antMatchers("/*/register", "/*/login").permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN") // 관리자페이지는 관리자 권한만
			.anyRequest().access("hasRole('USER') or hasRole('ADMIN')")
			
			.and()
			.formLogin()
			
			// vue 적용시, 원하는 login page mapping
			// .formLogin().loginPage("/user/login") // login page customize
			// formLogin에는 로그인 성공시 작동할 기능, 실패시 작동할 기능, 로그인 인증 URL 변경 등 다양한 기능들이 있다.
			
			.and()
	        .addFilterBefore(new JwtFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class); // ID, Password 검사 전에 jwt 필터 먼저 수
		
    }

	// 로그인시 DB에 BCrpyt로 저장되어있는 패스워드를 로그인할 때 입력한 비밀번호와 비교하기 위해 사용
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**",
				"/swagger/**"); // swagger 관련 요청은 허용
	}

}
