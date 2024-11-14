package com.example.tdd.profile;

import com.example.tdd.profile.bean.CoffeShop;
import com.example.tdd.profile.bean.Holder;
import com.example.tdd.profile.bean.LazyBean;
import com.example.tdd.profile.config.ApplicationConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(ApplicationConfig.class);

        var coffeShop = context.getBean(CoffeShop.class);
        coffeShop.orderCoffee();

        var holder = context.getBean(Holder.class);
        holder.fireLazyBean();
        var lazyBean = context.getBean(LazyBean.class);
        lazyBean.fireHolderBean();

    }
}
