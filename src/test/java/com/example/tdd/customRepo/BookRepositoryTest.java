package com.example.tdd.customRepo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase
class BookRepositoryTest {

    @Autowired
    BookRepositoryWithCustomImpl bookRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    void name() {
        for (int i = 0; i < 100; i++) {
            testEntityManager.persistAndFlush(new BookEntityCustomRepo("name"+i, "author"+i));
            testEntityManager.persistAndFlush(new AuthorEntity("name"+i, "author@gmail.com"+i, "surname"+i));
        }

        var actual = bookRepository.someCustomMethode(new BookEntityCustomRepo("name"+0, "author"));


        assertThat(actual.getName()).isEqualTo("name0");
    }


    @Test
    void testGetByAuthor() {
        for (int i = 0; i < 99; i++) {
            testEntityManager.persistAndFlush(new BookEntityCustomRepo("name"+i, "author"+i));
            testEntityManager.persistAndFlush(new AuthorEntity("author"+i, "author@gmail.com"+i, "surname"+i));
        }

        var actual = bookRepository.getBookByAuthor(new AuthorEntity("author"+0, "author@gmail.com"+0, "surname"+0));

        assertThat(actual.getName()).isEqualTo("name0");
    }


    @Test
    void testListGetByAuthorAndPageble() {
        for (int i = 0; i < 5; i++) {
            testEntityManager.persistAndFlush(new BookEntityCustomRepo("name"+i, "author"+i));
            testEntityManager.persistAndFlush(new AuthorEntity("author"+i, "author@gmail.com"+i, "surname"+i));
        }

        var actual = bookRepository.getListBook(
                new AuthorEntity("author"+0, "author@gmail.com"+0, "surname"+0),
                 PageRequest.of(0,2));

//        assertThat(actual.getName()).isEqualTo("name0");
        assertThat(actual).hasSize(2);


        var actualLast = bookRepository.getListBook(
                new AuthorEntity("author"+0, "author@gmail.com"+0, "surname"+0),
                PageRequest.of(3,2));

        assertThat(actualLast).hasSize(1);

    }

    @Test
    void testFindFirstSortUnsorted() {
        for (int i = 0; i < 5; i++) {
            testEntityManager.persistAndFlush(new BookEntityCustomRepo("name"+i, "author"+i));
            testEntityManager.persistAndFlush(new AuthorEntity("author"+i, "author@gmail.com"+i, "surname"+i));
        }

        var actual = bookRepository.findFirstBy(Sort.unsorted());

        assertThat(actual).isNotNull();
    }


    @Test
    void testFindFirstWithSorted() {
        for (int i = 0; i < 5; i++) {
            testEntityManager.persistAndFlush(new BookEntityCustomRepo("name"+i, "author"+i));
            testEntityManager.persistAndFlush(new AuthorEntity("author"+i, "author@gmail.com"+i, "surname"+i));
        }

        var actual = bookRepository.findFirstBy(Sort.by("name").descending());

        assertThat(actual).isNotNull();
        assertThat(actual.getName()).isEqualTo("name4");
    }

    @Test
    void testFindFirst3WithSorted() {
        for (int i = 0; i < 5; i++) {
            testEntityManager.persistAndFlush(new BookEntityCustomRepo("name"+i, "author"+i));
            testEntityManager.persistAndFlush(new AuthorEntity("author"+i, "author@gmail.com"+i, "surname"+i));
        }

        var actual = bookRepository.findFirst3By(Sort.by("name").descending());

        assertThat(actual).hasSize(3);
    }


    @Test
    void testFindTopWithSorted() {
        for (int i = 0; i < 5; i++) {
            testEntityManager.persistAndFlush(new BookEntityCustomRepo("name"+i, "author"+i));
            testEntityManager.persistAndFlush(new AuthorEntity("author"+i, "author@gmail.com"+i, "surname"+i));
        }

        var actual = bookRepository.findTopBy(Sort.by("name").descending());

        assertThat(actual.getName()).isEqualTo("name4");
    }


    @Test
    void testFindTop3WithSorted() {
        for (int i = 0; i < 5; i++) {
            testEntityManager.persistAndFlush(new BookEntityCustomRepo("name"+i, "author"+i));
            testEntityManager.persistAndFlush(new AuthorEntity("author"+i, "author@gmail.com"+i, "surname"+i));
        }

        var actual = bookRepository.findTop3By(Sort.by("name").descending());

        assertThat(actual).hasSize(3);
    }


    @Test
    void testWIthStreamable() {
        for (int i = 0; i < 5; i++) {
            testEntityManager.persistAndFlush(new BookEntityCustomRepo("name"+i, "author"+i));
            testEntityManager.persistAndFlush(new AuthorEntity("author"+i, "author@gmail.com"+i, "surname"+i));
        }

        var actual = bookRepository.findByNameContaining("name");
        assertThat(actual).hasSize(5);


        var actual2 = bookRepository.findByNameContaining("name").filter(f->f.name.equals("name4"));
        assertThat(actual2).hasSize(1);
        assertThat(actual2.stream().findFirst()).isNotEmpty();
        assertThat(actual2.stream().findFirst().get().getName()).isEqualTo("name4");
    }


    @Test
    void testWIthStreamable2() {
        for (int i = 0; i < 5; i++) {
            testEntityManager.persistAndFlush(new BookEntityCustomRepo("name"+i, "author"+i));
            testEntityManager.persistAndFlush(new AuthorEntity("author"+i, "author@gmail.com"+i, "surname"+i));
        }

        var actual = bookRepository.findByNameContaining("name")
                .and(bookRepository.findByNameContaining("4"))
                .and(bookRepository.findByNameContaining("3"));
        assertThat(actual).hasSize(7);

        var name4 = actual.filter(f -> f.name.equals("name4")).stream().findFirst();
        assertThat(name4.get().getName()).isEqualTo("name4");
    }


    @Test
    void testWIthStreamable3() {
        for (int i = 0; i < 5; i++) {
            testEntityManager.persistAndFlush(new BookEntityCustomRepo("name"+i, "author"+i));
            testEntityManager.persistAndFlush(new AuthorEntity("author"+i, "author@gmail.com"+i, "surname"+i));
        }

        var actual = bookRepository.findAllByNameContaining("name").count();
        assertThat(actual).isEqualTo(5L);
    }

    @Test
    void withStream() {
        for (int i = 0; i < 1000; i++) {
            testEntityManager.persistAndFlush(new BookEntityCustomRepo("name"+i, "author"+i));
            testEntityManager.persistAndFlush(new AuthorEntity("author"+i, "author@gmail.com"+i, "surname"+i));
        }

        try(Stream<BookEntityCustomRepo> stream=bookRepository.findAllByCustomQueryAndStream()){
            var list = stream.toList();
            assertThat(list).hasSize(1000);
        };
    }


    @Test
    @Transactional(readOnly = true)
    void withStreamAndHint() {
        for (int i = 0; i < 1000; i++) {
            testEntityManager.persistAndFlush(new BookEntityCustomRepo("name"+i, "author"+i));
            testEntityManager.persistAndFlush(new AuthorEntity("author"+i, "author@gmail.com"+i, "surname"+i));
        }

        try(Stream<BookEntityCustomRepo> stream=bookRepository.findAllByCustomQueryAndStreamWithHint()){
            var list = stream.toList();
            assertThat(list).hasSize(1000);
        };
    }


    @Test
    void withAsyncAndFuture() throws ExecutionException, InterruptedException {
        for (int i = 0; i < 100; i++) {
            testEntityManager.persistAndFlush(new BookEntityCustomRepo("name"+i, "author"+i));
            testEntityManager.persistAndFlush(new AuthorEntity("author"+i, "author@gmail.com"+i, "surname"+i));
        }

        var name0 = bookRepository.findFirstByName("name0");

        assertThat(name0.get().getName()).isEqualTo("name0");
    }

    @Test
    void testPage() throws ExecutionException, InterruptedException {
        for (int i = 0; i < 39; i++) {
            testEntityManager.persistAndFlush(new BookEntityCustomRepo("name"+i, "author"));
            testEntityManager.persistAndFlush(new AuthorEntity("author", "author@gmail.com"+i, "surname"+i));
        }

        var actual = bookRepository.findByAuthor("author", PageRequest.of(1, 20));

        assertThat(actual).hasSize(19);
        assertThat(actual.getTotalPages()).isEqualTo(2);
    }

    @Test
    void testSlice() throws ExecutionException, InterruptedException {
        for (int i = 0; i < 39; i++) {
            testEntityManager.persistAndFlush(new BookEntityCustomRepo("name", "author"));
            testEntityManager.persistAndFlush(new AuthorEntity("author", "author@gmail.com"+i, "surname"+i));
        }

        var actual = bookRepository.findByAuthorAndName("author", "name",
                PageRequest.of(1, 20));

        assertThat(actual).hasSize(19);
    }

    @Test
    void testGetListWithSort() throws ExecutionException, InterruptedException {
        for (int i = 0; i < 39; i++) {
            testEntityManager.persistAndFlush(new BookEntityCustomRepo("name", "author"+i));
            testEntityManager.persistAndFlush(new AuthorEntity("author", "author@gmail.com"+i, "surname"+i));
        }

        var actual = bookRepository.findByAuthorContaining("author", Sort.by("author" ).reverse());

        assertThat(actual).hasSize(39);
        assertThat(actual.get(0).getAuthor()).isEqualTo("author" + 9);
    }



    @Test
    void testGetListWithSortAndLimit() throws ExecutionException, InterruptedException {
        for (int i = 0; i < 39; i++) {
            testEntityManager.persistAndFlush(new BookEntityCustomRepo("name", "author"+i));
            testEntityManager.persistAndFlush(new AuthorEntity("author", "author@gmail.com"+i, "surname"+i));
        }

        var actual = bookRepository.findByAuthorContaining("author", Sort.by("author" ).descending(),
                Limit.of(5));

        assertThat(actual).hasSize(5);
        assertThat(actual.get(0).getAuthor()).isEqualTo("author" + 9);
    }


    @Test
    void testPageableAndList() throws ExecutionException, InterruptedException {
        for (int i = 0; i < 39; i++) {
            testEntityManager.persistAndFlush(new BookEntityCustomRepo("name", "author"+i));
            testEntityManager.persistAndFlush(new AuthorEntity("author", "author@gmail.com"+i, "surname"+i));
        }

        var actual = bookRepository.findCustom(PageRequest.of(1,20));

        assertThat(actual).hasSize(19);
        assertThat(actual.get(0).getAuthor()).isEqualTo("author20");
    }
}