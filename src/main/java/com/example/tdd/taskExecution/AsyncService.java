package com.example.tdd.taskExecution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Timer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

@Component
public class AsyncService {

    @Autowired
    FirstAsyncService firstAsyncService;

    @Autowired
    SecondAsyncService secondAsyncService;

    @Async
    Future<String> returnSomething(int i) throws InterruptedException {
        Thread.sleep(2000);
        return new FutureTask<>(() -> String.valueOf(i));
    }

    @Async
    public void asyncMethodWithVoidReturnType() {
        System.out.println("Executing async method... " + Thread.currentThread().getName());
    }

    @Async
    public Future<String> asyncMethodWithReturnTyp() {
        System.out.println("Executing async method... " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
            return new AsyncResult<>("Hello world!!!!");
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        return null;
    }

    public CompletableFuture<String> asyncMergeServicesResponse() throws InterruptedException {
        var stringCompletableFuture1 = firstAsyncService.asyncGetData();
        var stringCompletableFuture2 = secondAsyncService.asyncGetData();

        return stringCompletableFuture1.thenCompose(firstAsyncServiceValue ->
                stringCompletableFuture2.thenApply(secondAsyncServiceValue ->
                        firstAsyncServiceValue + stringCompletableFuture2));
    }

//    @Async("threadPoolTaskExecutor")
    @Async
    public void asyncMethodWithConfiguredExecutor() {
        System.out.println("Execute method with configured executor - "+Thread.currentThread().getName());
    }

    @Async
    public void asyncMethodWithException() {
        System.out.println("Execute method with configured executor - "+Thread.currentThread().getName());
        throw new RuntimeException("---- exception from "+ "asyncMethodWithException");
    }
}
