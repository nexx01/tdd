package com.example.tdd.caching2;

import jakarta.persistence.*;

@Entity
@Table(name = "my_users")
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    public MyUser(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public MyUser() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
