package com.usermanagement.user.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public String getAppName() {
        return "user-management";
    }
}