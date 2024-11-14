package com.example.tdd.taskExecution;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.SimpleAsyncTaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;

import java.time.Duration;
import java.util.concurrent.ScheduledExecutorService;

public class TaskScheduleTest {
    public static void main(String[] args) {
        var simpleAsyncTaskScheduler = new SimpleAsyncTaskScheduler();

        simpleAsyncTaskScheduler.schedule(() -> System.out.println("2"),
                new PeriodicTrigger(Duration.ofSeconds(5)));
    }
}
