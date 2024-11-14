package com.example.tdd.queryAndSpell.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class CarSpell {

    public CarSpell() {
    }

    private String name;
    private String type;

    @Id
    @GeneratedValue
    private Long id;

    public CarSpell(String prius, String hybrid) {
        this.name = prius;
        this.type = hybrid;
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
