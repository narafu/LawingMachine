package com.web.lawingmachine.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 커스텀한 OAuth2UserService DI.
    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

	// encoder를 빈으로 등록.
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // WebSecurity에 필터를 거는 게 훨씬 빠름. HttpSecrity에 필터를 걸면, 이미 스프링 시큐리티 내부에 들어온 상태기 때문에..
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/members/**","/image/**");    // /image/** 있는 모든 파일들은 시큐리티 적용을 무시한다.
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());    // 정적인 리소스들에 대해서 시큐리티 적용 무시.
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeRequests()
            .antMatchers("/", "/index", "/login/**", "/reg", "/oauth2/**", "/css/**", "/images/**", "/js/**", "/console/**", "/favicon.ico/**").permitAll()
            .antMatchers("/admin/**").hasRole("ADMIN")
            .anyRequest().authenticated()
        .and()
            .oauth2Login()
            .userInfoEndpoint()
            .userService(customOAuth2UserService) // 네이버 USER INFO의 응답을 처리하기 위한 설정
        .and()
            .defaultSuccessUrl("/loginSuccess")
            .failureUrl("/loginFailure")
        .and()
            .exceptionHandling()
            .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
        .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/");

    }
    
}
