package com.example.tdd.queryAndSpell.repo;

import com.example.tdd.queryAndSpell.SecurityConfiguration;
import com.example.tdd.queryAndSpell.domain.CarSpell;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase
    @Import(SecurityConfiguration.class)
class CarREpositoryTest {

    @Autowired
    CarREpository carREpository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    void name() {
        entityManager.persistAndFlush(new CarSpell("123", "456"));

        var byQuerySpell = carREpository.findByQuerySpell("123");

        assertThat(byQuerySpell).hasSize(1);
    }


    @Test
    void name2() {
        entityManager.persistAndFlush(new CarSpell("123", "456"));

        var byQuerySpell = carREpository.findAll();

        assertThat(byQuerySpell).hasSize(1);
    }
}