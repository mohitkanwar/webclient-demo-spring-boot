package com.mk.dummyserver;

import com.mk.dummyserver.model.Book;
import com.mk.dummyserver.model.Genre;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
public class BooksController {

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() throws InterruptedException {
        Thread.sleep(1000);
        return new ResponseEntity<>(new ArrayList<>(Database.getBooks()), HttpStatus.OK);
    }

    @GetMapping("/books/g/{genre}")
    public ResponseEntity<List<Book>> getBooksByGenre(@PathVariable("genre") Genre genre) throws InterruptedException {
        Thread.sleep(2000);

        return new ResponseEntity<>(Database.getBooks().stream().filter(book -> book.getGenre().equals(genre)).collect(Collectors.toList())
                , HttpStatus.OK);
    }

    @GetMapping("/books/a/{author}")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable("author") String author) throws InterruptedException {
        Thread.sleep(5000);
        return new ResponseEntity<>(Database.getBooks().stream().filter(book -> book.getAuthor().getName().toLowerCase().startsWith(author.toLowerCase())).collect(Collectors.toList())
                , HttpStatus.OK);
    }

    @GetMapping("/test/{condition}")
    public ResponseEntity<Boolean> checkErrors(@PathVariable("condition") String condition) throws InterruptedException {
        Thread.sleep(2000);
        if (condition.equalsIgnoreCase("slow")) {
            Thread.sleep(10000);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }

        if (condition.equalsIgnoreCase("ise")) {
            throw new RuntimeException("Errorrrrrrr");
        }

        return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);

    }
}
