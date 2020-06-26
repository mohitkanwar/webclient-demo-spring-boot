package com.mk.restclientexample;

import com.mk.restclientexample.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TechnicalAuthorsController {

    @GetMapping("/technicalauthors")
    public ResponseEntity<List<String>> getBooksByGenre() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Book[]> responseEntity = restTemplate.getForEntity("http://localhost:8080/books/g/TECHNOLOGY", Book[].class);

        HttpStatus statusCode = responseEntity.getStatusCode();
        List<String> authors = new ArrayList<>();
        if (statusCode.is2xxSuccessful()) {
            Book[] books = responseEntity.getBody();
            for (Book book : books) {
                System.out.println(book.getName());
                authors.add(book.getAuthor().getName());
            }
        }
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }
}
