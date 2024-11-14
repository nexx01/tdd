package com.example.tdd.integration;

import com.example.tdd.CarRepository;
import com.example.tdd.domain.Car;
import com.example.tdd.domain.Driver;
import com.example.tdd.domain.ParamHolder;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.domain.Specification;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase
public class annotationQueryTest {

    @Autowired
    CarRepository simpleRepository;


    @Autowired
    private TestEntityManager entityManager;
    @Test
    void when_simpleSelectWithQuery() {
        entityManager
                .persistAndFlush(new Car("test", "testType"));

        var withQuery = simpleRepository.getWithQuery("test", "testType").get();

        assertThat(withQuery.getName()).isEqualTo("test");
        assertThat(withQuery.getType()).isEqualTo("testType");
    }



    @Test
    void when_simpleSelectWithQueryAndSpell() {
        entityManager
                .persistAndFlush(new Car("test", "testType"));

        var withQuery = simpleRepository.getWithQueryAndSpell(new ParamHolder("test", "testType")).get();

        assertThat(withQuery.getName()).isEqualTo("test");
        assertThat(withQuery.getType()).isEqualTo("testType");
    }



    @Test
    void when_simpleSelectWithSpec() {
        entityManager
                .persistAndFlush(new Car("test", "testType"));

        var withQuery = simpleRepository
                .findAll(
 ((Specification<Car>) (root, query, criteriaBuilder) -> {
                            var equal = criteriaBuilder.equal(root.get("type"), "testType");
                            return equal;
                        }).and((Specification<Car>)
                                (root, query, criteriaBuilder) ->
                                        criteriaBuilder.equal(root.get("name"), "test"))).get(0);

        assertThat(withQuery.getName()).isEqualTo("test");
        assertThat(withQuery.getType()).isEqualTo("testType");
    }



    @Test
    void when_simpleSelectWithQueryAndSpec() {
        entityManager
                .persistAndFlush(new Car("test", "testType"));

        var withQuery = simpleRepository
                .findAllQuery(
                        ((Specification<Car>) (root, query, criteriaBuilder) -> {
                            var equal = criteriaBuilder.equal(root.get("type"), "testType");
                            return equal;
                        })
//                                .and((Specification<Car>)
//                                (root, query, criteriaBuilder) ->
//                                        criteriaBuilder.equal(root.get("name"), "test"))
                ).get(0);

        assertThat(withQuery.getName()).isEqualTo("test");
        assertThat(withQuery.getType()).isEqualTo("testType");
    }


    @Test
    void when_simpleSelectWithQueryAndSpec2() {
        entityManager
                .persistAndFlush(new Car("test", "testType"));
        entityManager
                .persistAndFlush(new Driver("driverName", "test"));

        var withQuery = simpleRepository
                .findAll(
                        CarRepository.Specs.byName("test")
                                .and(CarRepository.Specs.byType("testType"))
                ).get(0);

        assertThat(withQuery.getName()).isEqualTo("test");
        assertThat(withQuery.getType()).isEqualTo("testType");
    }

//
//    @Test
//    void when_simpleSelectWithSpecJoin() {
//        entityManager
//                .persistAndFlush(new Car("test", "testType"));
//        entityManager
//                .persistAndFlush(new Driver("driverName", "test"));
//
//        var withQuery = simpleRepository
//                .findAll(
//                        CarRepository.Specs.byDriver("driverName")
//                ).get(0);
//
//        assertThat(withQuery.getName()).isEqualTo("test");
//        assertThat(withQuery.getType()).isEqualTo("testType");
//    }
}
