package com.example.tdd.customRepo;

import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class CustomizedBookRepositoryImpl implements CustomizedBookRepository {

    private  final EntityManager entityManager;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public CustomizedBookRepositoryImpl(JpaContext jpaContext, NamedParameterJdbcTemplate jdbcTemplate) {
        this.entityManager = jpaContext.getEntityManagerByManagedType(BookEntityCustomRepo.class);
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public BookEntityCustomRepo someCustomMethode(BookEntityCustomRepo book) {
        var query = "select * from book b where b.name=:name";
        return (BookEntityCustomRepo)
                entityManager.createNativeQuery(query, BookEntityCustomRepo.class)
                .setParameter("name", book.getName())
                .getSingleResult();
    }

    @Override
    public BookEntityCustomRepo getBookByAuthor(AuthorEntity author) {
//        var map = Map.of("name", author.getName());

        var beanPropertySqlParameterSource = new BeanPropertySqlParameterSource(author);
        var s = "select b.id, b.name, b.author from book b left join author a on " +
                "b.author=a.name where a.name=:name";
        List<BookEntityCustomRepo> bookEntityCustomRepo = jdbcTemplate
                .query(s, beanPropertySqlParameterSource,  BeanPropertyRowMapper.newInstance(BookEntityCustomRepo.class));

        return bookEntityCustomRepo.get(0);
    }

    @Override
    public List<BookEntityCustomRepo> getListBook(AuthorEntity author,
                                                  Pageable pageable) {
        var beanPropertySqlParameterSource = new BeanPropertySqlParameterSource(author);
        var mapSqlParameterSource = new MapSqlParameterSource();
        var query = "select b.id, b.name, b.author from book b left join author a on " +
                "b.author=a.name limit "+pageable.getPageSize()
                + " offset "+ pageable.getOffset();
//        (page - 1) * size;
        return jdbcTemplate.query(query,
                beanPropertySqlParameterSource,
                BeanPropertyRowMapper.newInstance(BookEntityCustomRepo.class));
    }
}
