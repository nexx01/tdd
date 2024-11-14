
package com.example.tdd.taskExecution;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Instant;

@SpringBootApplication
@EnableCaching
public class TddApplication {

    public static void main(String[] args) {

        SpringApplication.run(TddApplication.class, args);
    }

}

@Component
class Some {

    @Scheduled(fixedDelay = 5000)
    public void doSomething() {
        System.out.println(Instant.now());
    }

    @Scheduled(initialDelay = 0, fixedDelay = 4000)
    public Mono<String> someMono() {
        return Mono.just("2");
    }



}
//
//@Component
//class SomeRu implements CommandLineRunner {
//    private final Some some;
//
//    SomeRu(Some some) {
//        this.some = some;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        some.someMono()
//                .subscribe(s-> System.out.println(s));
//    }
//}