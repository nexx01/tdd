package com.example.tdd.QuertyRewriter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.testcontainers.shaded.org.checkerframework.checker.units.qual.A;

//import java.awt.print.Book;
import java.awt.print.Book;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase
class MyQueryRewriterTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    void name() {
        testEntityManager.persistAndFlush(new BookEntity("name", "author"));

        var actual = bookRepository.findByNativeQuery("name").get(0);

        assertThat(actual.getName()).isEqualTo("name");
        assertThat(actual.getAuthor()).isEqualTo("author");
    }


    @Test
    void name2() {
        testEntityManager.persistAndFlush(new BookEntity("name", "author"));

        var actual = bookRepository.findByNativeQuery2("name").get(0);

        assertThat(actual.getName()).isEqualTo("name");
        assertThat(actual.getAuthor()).isEqualTo("author");
    }
}