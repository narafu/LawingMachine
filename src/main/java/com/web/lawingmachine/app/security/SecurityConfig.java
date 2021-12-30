package com.web.lawingmachine.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.web.lawingmachine.app.security.SocialType.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeRequests()
            .antMatchers("/", "/index", "/reg", "/oauth2/**", "/login/**", "/css/**", "/images/**", "/js/**", "/console/**", "/favicon.ico/**").permitAll()
            .antMatchers("/admin/**").hasRole("ADMIN")
            .antMatchers("/facebook").hasAuthority(FACEBOOK.getRoleType())
            .antMatchers("/google").hasAuthority(GOOGLE.getRoleType())
            .antMatchers("/kakao").hasAuthority(KAKAO.getRoleType())
            .antMatchers("/naver").hasAuthority(NAVER.getRoleType())
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
