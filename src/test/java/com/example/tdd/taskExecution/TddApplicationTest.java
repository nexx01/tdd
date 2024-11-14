package com.example.tdd.taskExecution;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
class TddApplicationTest {

    @Test
    void name() throws InterruptedException {
        Thread.sleep(140000);
    }
}