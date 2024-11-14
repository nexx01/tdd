package com.example.tdd.cashing;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class BreadService {

    @Cacheable(cacheNames = "bread")
    public Bread getBread() {
        return new Bread(String.valueOf(new Random().nextInt()));
    }


    @Cacheable(cacheNames = "bread", key = "#name")
    public Bread getBread(String name) {
        return new Bread(String.valueOf(new Random().nextInt()));
    }

    @Cacheable(cacheNames = "bread", condition = "#name.length<12")
    public Bread getBreadCacheByLength(String name) {
        return new Bread(String.valueOf(new Random().nextInt()));
    }

    @Cacheable(cacheNames = "bread", condition = "#name.length<12",
            unless = "#result.name.equals('some')")
    public Bread getBreadCacheByLengthUnless(String name) {
        return new Bread(String.valueOf(new Random().nextInt()));
    }


    @Cacheable(cacheNames = "bread", condition = "#name.length<12",
            unless = "#result.name.equals('some')")
    public Bread getBreadCacheByLengthUnless2(String name) {
        return new Bread("some");
    }
}
