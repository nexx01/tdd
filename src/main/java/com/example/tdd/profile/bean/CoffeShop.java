package com.example.tdd.profile.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
public class CoffeShop {

    private final Coffee coffee;

    @Value("${app.shop.name}")
    String appName;

    public CoffeShop(Coffee coffee) {
        this.coffee = coffee;
    }

    public void orderCoffee() {
        System.out.println(coffee.brew());
        System.out.println(appName);
    }
}
