package com.example.tdd.profile.bean;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class Holder {

    private final LazyBean lazyBean;

    public Holder(@Lazy LazyBean lazyBean) {
        this.lazyBean = lazyBean;
    }

    @PostConstruct
    public void init() {
        System.out.println("Holder bean constructed");
    }

    public void fireLazyBean() {
        System.out.println("from holder bean " + lazyBean.getInfo());
    }

    public  String getInfo() {
        return "Holder bean";
    }
}
