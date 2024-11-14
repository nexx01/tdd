package com.example.tdd;


import com.example.tdd.domain.Car;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class CarController {
    private final CarServise carServise;

    public CarController(CarServise carServise) {
        this.carServise = carServise;
    }

    @GetMapping("/cars/{name}")
    private Car getCar(@PathVariable String name) {
        return carServise.getCarsDetails(name);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void carNotFoundHandler(CarNotFoundException exception) {
    }
}
