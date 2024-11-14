package com.example.tdd.customRepo;

import org.springframework.data.util.Streamable;

import java.util.Iterator;

//@RequiredArgsConstructor(staticName = "of")
public class Books implements Streamable<BookEntityCustomRepo> {

    private final Streamable<BookEntityCustomRepo> streamable;

    public Books(Streamable<BookEntityCustomRepo> streamable) {
        this.streamable = streamable;
    }

    public long count() {
        return streamable
                .stream().count();
    }

    @Override
    public Iterator<BookEntityCustomRepo> iterator() {
        return streamable.iterator();
    }
}
