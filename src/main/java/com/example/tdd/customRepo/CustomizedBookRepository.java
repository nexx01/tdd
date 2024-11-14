package com.example.tdd.customRepo;

//import java.awt.print.Pageable;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomizedBookRepository {
    BookEntityCustomRepo someCustomMethode(BookEntityCustomRepo book);

    BookEntityCustomRepo getBookByAuthor(AuthorEntity author);

    List<BookEntityCustomRepo> getListBook(AuthorEntity author, Pageable pageable);
}
