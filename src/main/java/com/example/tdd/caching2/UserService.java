package com.example.tdd.caching2;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface UserService {



    MyUser create(MyUser user);

    @Cacheable(value = "users",key = "#name")
    MyUser create(String name, String email);

    MyUser get(Long id);

    List<MyUser> getAll();

    @Cacheable(value = "users", key = "#user.name")
    MyUser createOrReturnCached(MyUser user);

    @CachePut(value = "users", key = "#user.name")
    MyUser createAndRefreshCache(MyUser user);

    void delete(Long id);

    void deleteAndEvict(Long id);

}
