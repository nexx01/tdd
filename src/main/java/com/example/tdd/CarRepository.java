package com.example.tdd;

import com.example.tdd.domain.Car;
import com.example.tdd.domain.Driver;
import com.example.tdd.domain.ParamHolder;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface CarRepository extends CrudRepository<Car, Long>, JpaSpecificationExecutor<Car> {
    Car findByName(String name);

    @Query("from CarSpell c where c.name =:name and c.type= :type")
    Optional<Car> getWithQuery(@Param("name") String name, @Param("type") String type);

    @Query("from CarSpell c where c.name = :#{#holder.name} and c.type=:#{#holder.type}")
    Optional<Car> getWithQueryAndSpell(ParamHolder holder);


    //     @Query("from Car c where c.name =:name2")
//     Optional<Car> getWithQueryAndSpecification(@Param("name2") String name, Specification<Car> spec);
    List<Car> findAll(Specification<Car> spec);

    @Query("from CarSpell c")
    List<Car> findAllQuery(Specification<Car> spec);

    //     @Query("from Car c join Car c2 on c2.name=:namez ")
//     @Query("select c from Car c where c.type =:type ")
//     List<Car> findAllQueryParam(@Param("type") String name, Specification<Car> spec);
    interface Specs {
        static Specification<Car> byName(String name) {
            return (root, query, criteriaBuilder)
                    -> criteriaBuilder.equal(root.get("name"), name);
        }

        static Specification<Car> byType(String type) {
            return (root, query, criteriaBuilder)
                    -> criteriaBuilder.equal(root.get("type"), type);
        }
        //https://self-learning-java-tutorial.blogspot.com/2014/02/blog-post.html
//        static Specification<Car> byDriver(String driver) {
//            return new Specification<Car>() {
//                @Override
//                public Predicate toPredicate(Root<Car> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                    Join<Car, Driver> join = root.join("name");
//                    return criteriaBuilder.equal(join.get("carName"), driver);
//                }
//            };
//        }
    }
}
