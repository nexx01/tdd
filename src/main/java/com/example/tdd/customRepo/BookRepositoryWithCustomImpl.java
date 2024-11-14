package com.example.tdd.customRepo;

import jakarta.persistence.QueryHint;
//import org.hibernate.query.Page;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.jpa.repository.QueryRewriter;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import static org.hibernate.annotations.QueryHints.COMMENT;
import static org.hibernate.annotations.QueryHints.READ_ONLY;
import static org.hibernate.jpa.HibernateHints.HINT_CACHEABLE;
import static org.hibernate.jpa.HibernateHints.HINT_FETCH_SIZE;


public interface BookRepositoryWithCustomImpl extends JpaRepository<BookEntityCustomRepo, Long>, CustomizedBookRepository {

    @Query(value = "SELECT original_book_alias.* FROM books original_book_alias",
    nativeQuery = true, queryRewriter = MyQueryRewriter.class)
    List<BookEntityCustomRepo> findByNativeQuery(String query);


    @Query(value = "SELECT original_book_alias.* FROM books original_book_alias where original_book_alias.name = :query",
            nativeQuery = true, queryRewriter = MyQueryRewriter2.class)
    List<BookEntityCustomRepo> findByNativeQuery2(@Param("query") String query);

    BookEntityCustomRepo findFirstBy(Sort sort);

    List<BookEntityCustomRepo> findFirst3By(Sort sort);

    BookEntityCustomRepo findTopBy(Sort sort);

    List<BookEntityCustomRepo> findTop3By(Sort sort);

    Streamable<BookEntityCustomRepo> findByNameContaining(String name);

    Books findAllByNameContaining(String name);

    @Query("select u from BookEntityCustomRepo u")
    Stream<BookEntityCustomRepo> findAllByCustomQueryAndStream();

    @Query("select u from BookEntityCustomRepo u")
    @QueryHints(value = {
            @QueryHint(name = HINT_FETCH_SIZE, value = "25"),
            @QueryHint(name = HINT_CACHEABLE, value = "false"),
            @QueryHint(name = READ_ONLY, value = "true"),
            @QueryHint(name = COMMENT, value = "111111")
    })
    Stream<BookEntityCustomRepo> findAllByCustomQueryAndStreamWithHint();

    @Async
    Future<BookEntityCustomRepo> findFirstByName(String name);

    @Async
    CompletableFuture<BookEntityCustomRepo> findOneByName(String name);


    @Query("select u from BookEntityCustomRepo u")
    Stream<BookEntityCustomRepo> streamAllPaged(Pageable pageable);

    Page<BookEntityCustomRepo> findByAuthor(String author, Pageable pageable);

    Slice<BookEntityCustomRepo> findByAuthorAndName(String author, String name, Pageable pageable);

    List<BookEntityCustomRepo> findByAuthorContaining(String author, Sort sort);

    List<BookEntityCustomRepo> findByAuthorContaining(String author, Sort sort, Limit limit);

    @Query("select u from BookEntityCustomRepo u")
    List<BookEntityCustomRepo> findCustom( Pageable pageable);

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