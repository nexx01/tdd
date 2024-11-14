//package com.example.tdd.queryAndSpell.domain;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.Id;
//
//import static jakarta.persistence.GenerationType.AUTO;
//
//@Entity
//public class Driver {
//    @Id
//    @GeneratedValue(strategy = AUTO)
//    private Long id;
//
//    private String name;
//
//    public Driver(String name, String carName) {
//        this.name = name;
//        this.carName = carName;
//    }
//
//    //    @OneToOne
//    private String carName;
//
//    public Driver() {
//
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Long getId() {
//        return id;
//    }
//}
