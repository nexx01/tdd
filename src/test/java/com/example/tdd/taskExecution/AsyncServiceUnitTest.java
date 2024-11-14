package com.example.tdd.taskExecution;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringAsyncConfig.class, loader= AnnotationConfigContextLoader.class)
class AsyncServiceUnitTest {

    @Autowired
    private
    AsyncService asyncService;


    @Test
    void testAsyncAnnotationForMethodsWithVoidReturnType() throws InterruptedException {
        asyncService.asyncMethodWithVoidReturnType();
        System.out.println("Test execute in thread: "+Thread.currentThread().getName());
        Thread.sleep(10000);
    }

    @Test
    void testAsyncAnnotationForMethodsWithReturnType() throws InterruptedException, ExecutionException {
        var stringFuture = asyncService.asyncMethodWithReturnTyp();

        while (true) {
            if (stringFuture.isDone()) {
                System.out.println(stringFuture.get());
                break;
            }
            Thread.sleep(1000);
        }
    }

    @Test
    void testMergedAsyncValue() throws InterruptedException, ExecutionException {
        var stringCompletableFuture = asyncService.asyncMergeServicesResponse();

//        System.out.println("+++  " + stringCompletableFuture.get());

        while (true) {
            if(stringCompletableFuture.isDone()) {
                System.out.println("Result from asynchronous process - " + stringCompletableFuture.get());
                break;
            }

            System.out.println("Continue doing something else.");
            Thread.sleep(1000);
        }
    }

    @Test
    void testWithConfiguredExecutor() {
        asyncService.asyncMethodWithConfiguredExecutor();
    }

    @Test
    void testWithException() {
        asyncService.asyncMethodWithException();
    }

}