package com.example.tdd.stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

public class StreamTest {

    public static void main(String[] args) {
        var b = Stream.of(1, 2, 3)
                .anyMatch(i -> 4 == i);
        Assertions.assertThat(b).isEqualTo(false);
    }

    @Test
    void name() {
        var b = Stream.of(1, 2, 3)
                .anyMatch(i -> 3 == i);
        Assertions.assertThat(b).isEqualTo(true);
    }


    @Test
    void name2() {
        var b = List.<Integer>of().stream()

                .anyMatch(i -> 3 == i);
        Assertions.assertThat(b).isEqualTo(true);
    }
}
