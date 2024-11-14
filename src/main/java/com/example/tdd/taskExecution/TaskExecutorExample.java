package com.example.tdd.taskExecution;

import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class TaskExecutorExample {

    private class MessagePrinterTask implements Runnable {
        private String message;

        public MessagePrinterTask(String message) {
            this.message = message;
        }

        @Override
        public void run() {
            System.out.println(message);
        }
    }

    private TaskExecutor taskExecutor;

    public TaskExecutorExample(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public void printMessage() {
        for (int i = 0; i < 25; i++) {
            taskExecutor.execute(new MessagePrinterTask("Message " + i));
        }
    }

    public static void main(String[] args) {
//        var taskExecutor1 = new SimpleAsyncTaskExecutor();
        var taskExecutor1 = new ThreadPoolTaskExecutor();
        taskExecutor1.setCorePoolSize(100);
        taskExecutor1.setQueueCapacity(4);
        taskExecutor1.initialize();

        var taskExecutorExample = new TaskExecutorExample(taskExecutor1);
        taskExecutorExample.printMessage();
        taskExecutor1.shutdown();
    }
}
