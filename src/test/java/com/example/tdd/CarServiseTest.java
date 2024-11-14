package com.example.tdd;

import com.example.tdd.domain.Car;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.StringJoiner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CarServiseTest {

    @Spy
    @InjectMocks
    private CarServise subj;

    @Mock
    CarRepository carRepository;

    @Test
    void getCarDetails_returnCarInfo() {
        given(carRepository.findByName("prius")).willReturn(new Car("prius", "hybrid"));

        var actual = subj.getCarsDetails("prius");

        Assertions.assertThat(actual.getName()).isEqualTo("prius");
        Assertions.assertThat(actual.getType()).isEqualTo("hybrid");
    }

    @Test()
    void getCarDtails_whenCarNotFound() {
        given(carRepository.findByName("prius")).willReturn(null);

        assertThatThrownBy(() -> subj.getCarsDetails("prius")).isInstanceOf(CarNotFoundException.class);
    }

    @Test
    void name() {
        var stringJoiner = new StringJoiner(", ");
        for (int i = 0; i < 10; i++) {
            stringJoiner.add(""+i);
        }

        var objects = new ArrayList<Object>();
//        objects.set()


        assertThat(stringJoiner.toString()).isEqualTo("0, 1, 2, 3, 4, 5, 6, 7, 8, 9");    }
}