package com.example.tdd.profile.bean;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class LazyBean {

    private final Holder holder;

    public LazyBean(Holder holder) {
        this.holder = holder;
    }

    @PostConstruct
    public void init() throws Exception {
        System.out.println("Lazy bean constructed");
    }

    public String getInfo() {
        return "Lazy bean";
    }

    public void fireHolderBean() {
        System.out.println("From lazy bean "+holder.getInfo());
    }
}
