package com.example.tdd.caching2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    void get() {
        var user1 = userService.create(new MyUser("Vasya", "vasya@mail.ru"));
        var user2 = userService.create(new MyUser("Kolya", "kolya@mail.ru"));

        getAndPrint(user1.getId());
        getAndPrint(user2.getId());
        getAndPrint(user1.getId());
        getAndPrint(user2.getId());
    }

    private void getAndPrint(Long id) {
        System.out.println("user found " + userService.get(id));
    }


    @Test
    void create() {
        createAndPrint("Ivan", "ivan@mail.ru");
        createAndPrint("Ivan", "ivan1122@mail.ru");
        createAndPrint("Sergey", "ivan@mail.ru");

        System.out.println("all entries are below");

        userService.getAll().forEach(u-> System.out.println(u.toString()));
    }

    private void createAndPrint(String name, String email) {
        System.out.println("created user: {}" + userService.create(name, email));
    }

    @Test
    public void createAndRefresh() {
        MyUser user1 = userService.createOrReturnCached(new MyUser("Vasya", "vasya@mail.ru"));
        System.out.println("created user1: {}"+user1);

        MyUser user2 = userService.createOrReturnCached(new MyUser("Vasya", "misha@mail.ru"));
        System.out.println("created user2: {}"+user2);

        MyUser user3 = userService.createAndRefreshCache(new MyUser("Vasya", "kolya@mail.ru"));
        System.out.println("created user3: {}"+user3);

        MyUser user4 = userService.createOrReturnCached(new MyUser("Vasya", "petya@mail.ru"));
        System.out.println("created user4: {}"+user4);
    }

    @Autowired
    ApplicationContext applicationContext;
    @Test
    void delete() {

        var vasya = userService.create(new MyUser("Vasya", "vasya@mail.ru"));
        System.out.println(userService.get(vasya.getId()));

        var vasya1 = userService.create(new MyUser("Vasya", "vasya@mail.ru"));
        System.out.println(userService.get(vasya1.getId()));

        userService.delete(vasya.getId());
        userService.deleteAndEvict(vasya1.getId());

        System.out.println(userService.get(vasya.getId()));
        System.out.println(userService.get(vasya1.getId()));
    }
}