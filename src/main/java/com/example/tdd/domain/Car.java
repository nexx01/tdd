package com.example.tdd.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Car {

    public Car() {
    }

    private String name;
    private String type;


    @Id
//    @GeneratedValue
    private Long id;

    public Car(String prius, String hybrid) {
        this.name = prius;
        this.type = hybrid;
    }

    public Car(Long id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
