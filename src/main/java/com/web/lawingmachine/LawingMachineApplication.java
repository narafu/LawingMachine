package com.web.lawingmachine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
@EnableJpaAuditing
public class LawingMachineApplication {

    public static void main(String[] args) {
        SpringApplication.run(LawingMachineApplication.class, args);
    }

    @Bean
    public AuditorAware<String> auditorProvider() {
        // session에서 꺼내서 아이디를 넣어준다.
        return () -> Optional.of(UUID.randomUUID().toString());
    }

}
