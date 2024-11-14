package com.example.tdd.caching2;

//import jakarta.persistence.Cacheable;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final MyUserRepository userRepository;

    public UserServiceImpl(MyUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public MyUser create(MyUser user) {
        return userRepository.save(user);
    }

    @Cacheable(value = "users", key = "#name")
    @Override
    public MyUser create(String name, String email) {
        System.out.println("creating user with parameters: "+name+"  "+email);
        return userRepository.save(new MyUser(name, email));
    }

    @Override
    @Cacheable("users")
    public MyUser get(Long id) {

        return userRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("User not found"));
    }

    @Override
    public List<MyUser> getAll() {
        return userRepository.findAll();
    }

    @Cacheable(value = "users", key = "#user.name")
    @Override
    public MyUser createOrReturnCached(MyUser user) {
        System.out.printf("creating user: {%s}", user);
        return userRepository.save(user);
    }

    @CachePut(value = "users", key = "#user.name")
    @Override
    public MyUser createAndRefreshCache(MyUser user) {
        System.out.printf("creating user: {%s}", user);
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        System.out.println("deleting user by id: " + id);
        userRepository.deleteById(id);
    }

    @Override
    @CacheEvict("users")
    public void deleteAndEvict(Long id) {
        System.out.println("deleting user by id: " + id);
        userRepository.deleteById(id);
    }
}
