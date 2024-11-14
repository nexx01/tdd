package com.example.tdd;

import com.example.tdd.domain.Car;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CarServise {
    private final CarRepository repository;

    public CarServise(CarRepository repository) {
        this.repository = repository;
    }

    @Cacheable("cars")
    public Car getCarsDetails(String name) {
        var car = repository.findByName(name);
        if(car == null) throw new CarNotFoundException();
        return car;
    }


    @Transactional
    public Car getCarsDetailsWithoutCache(String name) {
        var car = repository.findByName(name);
//        if(car == null) throw new CarNotFoundException();
        return car;
    }



    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    @Transactional
    public Optional<Car> getCarsDetailsWithoutCache(Long id) {
        var car = repository.findById(id);
//        if(car == null) throw new CarNotFoundException();
        return car;
    }

}
