package com.example.tdd.algoritm;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


public class LongestRepeatedCharacterTest {


    @ParameterizedTest
    @CsvSource({
            "aabbbaaabbbcccaaaaaaadddaa, 7"
    })
    void name(String str, int expected) {
        int count = 1;
        int max = 0;


        for (int i = 1; i <str.length(); i++) {
            char previous = str.charAt(i - 1);
            char ch = str.charAt(i);
            if (previous == ch) {
                count++;
            } else {
                max = Math.max(max, count);
                count = 1;
            }
        }

        assertThat(max).isEqualTo(expected);
    }
}

