package com.example.tdd.queryByExample;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;

@DataJpaTest
@AutoConfigureTestDatabase
class PersonRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    PersonRepository subj;

    @Test
    void testQueryByExample() {
        for (int i = 0; i < 10; i++) {
            testEntityManager.persistAndFlush(new Person(new Address("12"+i), "firstName"+i, "lastName"+i));
        }

        var person = new Person();
        person.setFirstName("firstName7");
        var example = Example.of(person);

        var one = subj.findOne(example);

        assertThat(one.get().getFirstName()).isEqualTo("firstName7");
    }


    @Test
    void testQueryAllByExample() {
        for (int i = 0; i < 10; i++) {
            testEntityManager.persistAndFlush(new Person(new Address("12"+i), "firstName"+i, "lastName"+i));
        }

        var person = new Person();
        person.setFirstName("firstName7");
        var example = Example.of(person);

        var one = subj.findAll(example);

        assertThat(one.get(0).getFirstName()).isEqualTo("firstName7");
    }


    @Test
    void testQueryAllByExampleMatching() {
        for (int i = 0; i < 10; i++) {
            testEntityManager.persistAndFlush(new Person(new Address("12"+i), "firstName"+i, "lastName"+i));
        }

        var person = new Person();
        person.setFirstName("firstName");

        var matcher = ExampleMatcher.matchingAny()
                .withIgnorePaths("lastName")
                .withIncludeNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        var example = Example.of(person, matcher);

        var one = subj.findAll(example);

        assertThat(one).hasSize(10);
        assertThat(one.get(0).getFirstName()).isEqualTo("firstName0");
    }


    @Test
    void testQueryAllByExampleMatching2() {
        for (int i = 0; i < 10; i++) {
            testEntityManager.persistAndFlush(new Person(new Address("12"+i), "firstName"+i, "lastName"+i));
        }

        var person = new Person();
        person.setFirstName("firstName");

        var matcher = ExampleMatcher.matching()
                .withMatcher("firstName", startsWith())
                .withMatcher("lastName",startsWith().ignoreCase());

        Example<Person> example = Example.of(person, matcher);

        var one = subj.findAll(example);

        assertThat(one).hasSize(10);
        assertThat(one.get(0).getFirstName()).isEqualTo("firstName0");
    }
}