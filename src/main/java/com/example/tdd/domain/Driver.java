package com.example.tdd.domain;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
public class Driver {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String name;

    public Driver(String name, String carName) {
        this.name = name;
        this.carName = carName;
    }

    //    @OneToOne
    private String carName;

    public Driver() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
