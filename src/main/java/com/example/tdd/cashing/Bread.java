package com.example.tdd.cashing;

import jakarta.persistence.Cacheable;

import java.util.Objects;

//@Cacheable
public class Bread {

    String name;

    public Bread(String name) {
        this.name = name;
    }

    public Bread() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bread bread = (Bread) o;
        return Objects.equals(name, bread.name);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
