package com.example.tdd.taskExecution;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class FirstAsyncService {


    @Async
    public CompletableFuture<String> asyncGetData( )throws InterruptedException {
        System.out.println("--------- Execute method asynchronously " + Thread.currentThread().getName());
        Thread.sleep(4000);
        return new AsyncResult<>(super.getClass().getSimpleName() + " response !!!").completable();
    }
}
