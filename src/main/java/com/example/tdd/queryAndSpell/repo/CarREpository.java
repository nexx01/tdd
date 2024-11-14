package com.example.tdd.queryAndSpell.repo;

import com.example.tdd.domain.Car;
import com.example.tdd.queryAndSpell.domain.CarSpell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarREpository extends JpaRepository<CarSpell,Long> {

    @Query("select c from Car  c where c.name=:s")
    List<Car> findByQuerySpell(String s);

}
