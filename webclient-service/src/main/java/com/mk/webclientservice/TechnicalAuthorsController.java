package com.mk.webclientservice;


import com.mk.webclientservice.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TechnicalAuthorsController {

    @GetMapping("/technicalauthors")
    public ResponseEntity<List<String>> getBooksByGenre() {
        List<String> authors = new ArrayList<>();
        WebClient.Builder webClientBuilder = WebClient.builder();

        ClientResponse clientResponse = webClientBuilder.build()
                .get()
                .uri("http://localhost:8080/books/g/TECHNOLOGY")
                .exchange()
                .block();

        if (clientResponse.statusCode().is2xxSuccessful()) {
            Book[] books = clientResponse.bodyToMono(Book[].class).block();
            for (Book book : books) {
                System.out.println(book.getName());
                authors.add(book.getAuthor().getName());
            }
        }

        return new ResponseEntity<>(authors, HttpStatus.OK);
    }
}
