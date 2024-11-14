package com.example.tdd.QuertyRewriter;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryRewriter;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    @Query(value = "SELECT original_book_alias.* FROM books original_book_alias",
    nativeQuery = true, queryRewriter = MyQueryRewriter.class)
    List<BookEntity> findByNativeQuery(String query);


    @Query(value = "SELECT original_book_alias.* FROM books original_book_alias where original_book_alias.name = :query",
            nativeQuery = true, queryRewriter = MyQueryRewriter2.class)
    List<BookEntity> findByNativeQuery2(@Param("query") String query);


}

@Component
class MyQueryRewriter implements QueryRewriter {

    @Override
    public String rewrite(String query, Sort sort) {
        
        return query.replaceAll("original_book_alias.*", "original_book_alias");
    }
}


@Component
class MyQueryRewriter2 implements QueryRewriter {

    @Override
    public String rewrite(String query, Sort sort) {
        return query.replaceAll("original_book_alias.name", "original_book_alias.author");
    }
}