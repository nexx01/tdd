package com.example.tdd.customRepo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Table(name = "book")
public class BookEntityCustomRepo {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    String name;

    String author;

    public BookEntityCustomRepo() {
    }

    public BookEntityCustomRepo(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
