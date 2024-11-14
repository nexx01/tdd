package com.example.tdd.taskExecution;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AsyncServiceTest {

    @Autowired
    AsyncService asyncService;

    @Test
    void test() throws InterruptedException, ExecutionException {

        System.out.println(asyncService.returnSomething(1).get());
    }
}