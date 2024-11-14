package com.example.tdd.cashing;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ExtendWith(SpringExtension.class)
class CachingApplicationTest {
    @Autowired
    BreadService breadService;

    @Test
    void test() {
        System.out.println("------ Success start test");
    }

    @Test
    void name() {
        var bread1 = breadService.getBread();

        var bread2 = breadService.getBread();

        Assertions.assertThat(bread1.getName()).isEqualTo(bread2.getName());
    }


    @Test
    void name2() {
        var bread1 = breadService.getBread("some");

        var bread2 = breadService.getBread("some");

        Assertions.assertThat(bread1.getName()).isEqualTo(bread2.getName());
    }


    @Test
    void name3() {
        var bread1 = breadService.getBread("some");

        var bread2 = breadService.getBread("some1");

        Assertions.assertThat(bread1.getName()).isNotEqualTo(bread2.getName());
    }


    @Test
    void name4() {
        var bread1 = breadService.getBreadCacheByLength("some");

        var bread2 = breadService.getBreadCacheByLength("some");

        Assertions.assertThat(bread1.getName()).isEqualTo(bread2.getName());
    }


    @Test
    void name5() {
        var bread1 = breadService.getBreadCacheByLength("some123456789111222");

        var bread2 = breadService.getBreadCacheByLength("some123456789111222");

        Assertions.assertThat(bread1.getName()).isNotEqualTo(bread2.getName());
    }


    @Test
    void name6() {
        var bread1 = breadService.getBreadCacheByLengthUnless("some123456789111222");

        var bread2 = breadService.getBreadCacheByLengthUnless("some123456789111222");

        Assertions.assertThat(bread1.getName()).isNotEqualTo(bread2.getName());
    }


    @Test
    void name7() {
        var bread1 = breadService.getBreadCacheByLengthUnless2("some123456789111222");

        var bread2 = breadService.getBreadCacheByLengthUnless2("some123456789111222");

        Assertions.assertThat(bread1.getName()).isEqualTo(bread2.getName());
    }

}