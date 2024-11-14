package com.example.tdd.QuertyRewriter;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Table(name = "books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    String name;

    String author;

    public BookEntity() {
    }

    public BookEntity(String name, String author) {
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
