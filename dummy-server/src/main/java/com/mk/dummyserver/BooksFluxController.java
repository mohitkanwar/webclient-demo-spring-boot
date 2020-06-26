package com.mk.dummyserver;

import com.mk.dummyserver.model.Author;
import com.mk.dummyserver.model.Book;
import com.mk.dummyserver.model.Genre;
import com.mk.dummyserver.model.Publication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

@RestController
public class BooksFluxController {

    private List<String> bookNames = new ArrayList<>();
    private List<String> authors = new ArrayList<>();
    private List<String> publications = new ArrayList<>();

    public BooksFluxController() {
        this.bookNames.add("Book");
        this.bookNames.add("Another Book");
        this.bookNames.add("One More Book");
        this.bookNames.add("Awesome Book");
        this.bookNames.add("Super Awesome Book");
        this.bookNames.add("Technical Book");
        this.bookNames.add("Another Technical Book");
        this.bookNames.add("One more Technical Book");
        this.bookNames.add("Fictious Book");
        this.bookNames.add("Amazing Fictious Book");
        this.bookNames.add("A bad name");
        this.bookNames.add("A good name");
        this.bookNames.add("A good name for a book");
        this.bookNames.add("A bad name for a book");
        this.bookNames.add("A amazing name for a book");

        this.authors.add("Author");
        this.authors.add("Author 1");
        this.authors.add("Author 2");
        this.authors.add("Author 3");
        this.authors.add("Author 4");
        this.authors.add("Author 5");
        this.authors.add("Author 6");
        this.authors.add("Author 7");
        this.authors.add("Author 8");
        this.authors.add("Author 9");
        this.authors.add("Author 10");

        this.publications.add("Publication");
        this.publications.add("Publication 1");
        this.publications.add("Publication 2");
        this.publications.add("Publication 3");
        this.publications.add("Publication 4");
        this.publications.add("Publication 5");
        this.publications.add("Publication 6");
        this.publications.add("Publication 7");
        this.publications.add("Publication 8 ");
        this.publications.add("Publication 9");
        this.publications.add("Publication 10");


    }

    public static int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    @GetMapping(path = "/books/stream",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Book> feed() {

        return Flux.interval(Duration.ofSeconds(1))
                .onBackpressureDrop()
                .map(this::generateBook)
                .flatMapIterable(x -> x);

    }

    private List<Book> generateBook(long interval) {
        Calendar cal = Calendar.getInstance();

        cal.set(getRandomYear(), getRandomMonth(), getRandomDate());

        Book book = new Book(getRandomBookName(),
                new Author(getRandomAuthor()),
                new Publication(getRandomPublication()),
                Genre.randomGenre(),
                cal.getTime()
        );

        return Collections.singletonList(book);

    }

    private String getRandomPublication() {
        int index = randBetween(0, publications.size());
        return publications.get(index);
    }

    private String getRandomBookName() {
        int index = randBetween(0, bookNames.size());
        return bookNames.get(index);
    }

    private String getRandomAuthor() {
        int index = randBetween(0, authors.size());
        return authors.get(index);
    }

    private int getRandomDate() {
        return randBetween(1, 28);
    }

    private int getRandomMonth() {
        return randBetween(1, 12);
    }

    private int getRandomYear() {
        return randBetween(1900, 2010);
    }
}
