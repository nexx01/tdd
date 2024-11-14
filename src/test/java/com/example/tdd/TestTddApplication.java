package com.example.tdd;

import org.springframework.boot.SpringApplication;

public class TestTddApplication {

    public static void main(String[] args) {
        SpringApplication.from(TddApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
