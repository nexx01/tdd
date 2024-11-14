package com.example.tdd.profile.bean;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("espesso")
public class Espesso implements Coffee {
    @Override
    public String brew() {
        return "Espesso";
    }
}
