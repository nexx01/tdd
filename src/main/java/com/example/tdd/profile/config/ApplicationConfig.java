package com.example.tdd.profile.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@ComponentScan("com.example.tdd.profile")
@Configuration
@PropertySource("classpath:default-profile-application.properties")
public class ApplicationConfig {


    @Configuration
    @Profile("latte")
    @PropertySource("classpath:latte-profile-application.properties")
    static class LatteConfig {

    }


    @Configuration
    @Profile("espesso")
    @PropertySource("classpath:espesso-profile-application.properties")
    static class EspessoConfig {

    }
}
