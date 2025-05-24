package com.trippal.trippal_backend.config;

import com.trippal.trippal_backend.repository.UserInfoRepository;
import com.trippal.trippal_backend.service.UserInfoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// Configuration class for defining security-related beans
@Configuration
public class UserSecurityBeansConfig {

    @Bean
    public UserInfoService userInfoService(UserInfoRepository repository, PasswordEncoder encoder) {
        return new UserInfoService(repository, encoder);
    }
}
