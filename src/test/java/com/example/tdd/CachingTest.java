package com.example.tdd;

import com.example.tdd.domain.Car;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE
//,classes = {CachingConfig.class,CarServise.class,CarRepository.class}
)
//@AutoConfigureCache
@AutoConfigureTestDatabase

public class CachingTest { //не может быть теста на уровне модуля

    @Autowired
    private CarServise carServise;

    @SpyBean
    private CarRepository repository;

    @Test
    void caching() {
        BDDMockito.given(repository.findByName(anyString())).willReturn(new Car("prius", "hybrid"));

        carServise.getCarsDetails("prius");
        carServise.getCarsDetails("prius");

        BDDMockito.verify(repository, Mockito.times(1)).findByName("prius");
    }
}
