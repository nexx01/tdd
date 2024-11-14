package com.example.tdd.algoritm;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class CountCharacterTest {

    @ParameterizedTest
    @CsvSource({
            "AAAAABB, A5B2",
            "AAAAABBC, A5B2C1",
            "AAAABB, A4B2",
            "ABB, A1B2",
            "BB, B2",
    })
    void name(String str, String expected) {
        int count = 1;
        var result = new StringBuilder();

        for (int i = 1; i < str.length(); i++) {
            char prev = str.charAt(i-1);
            char current = str.charAt(i);
            if (current == prev) {
                count++;
            } else {
                result.append(prev).append(count);
                count = 1;
            }
        }
        result.append(str.charAt(str.length() - 1)).append(count);

        assertThat(result.toString()).isEqualTo(expected);
    }
}
