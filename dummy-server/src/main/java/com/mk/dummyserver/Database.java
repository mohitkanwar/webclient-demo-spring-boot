package com.mk.dummyserver;

import com.mk.dummyserver.model.Author;
import com.mk.dummyserver.model.Book;
import com.mk.dummyserver.model.Genre;
import com.mk.dummyserver.model.Publication;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Component
public class Database {
    private static final Set<Book> books = new HashSet<>();

    public Database() {
        Calendar cal = Calendar.getInstance();
        books.add(getTheLastLecture(cal));
        books.add(getHarryPotter1(cal));
        books.add(getHarryPotter2(cal));
        books.add(getJavaPuzzelers(cal));
    }

    public static Set<Book> getBooks() {
        return books;
    }

    private Book getJavaPuzzelers(Calendar cal) {


        cal.set(2005, Calendar.JUNE, 24);
        return new Book("Java Puzzlers: Traps, Pitfalls, and Corner Cases",
                new Author("Joshua Bloch"),
                new Publication("Addison-Wesley"),
                Genre.TECHNOLOGY,
                cal.getTime()
        );
    }

    private Book getHarryPotter1(Calendar cal) {
        cal.set(1997, Calendar.JUNE, 26);
        return new Book("Harry Potter and the Philosopher's Stone",
                new Author("J. K. Rowling"),
                new Publication("Bloomsbury"),
                Genre.FANTASY,
                cal.getTime()
        );
    }

    private Book getHarryPotter2(Calendar cal) {
        cal.set(1998, Calendar.JULY, 2);
        return new Book("Harry Potter and the Chamber of Secrets",
                new Author("J. K. Rowling"),
                new Publication("Bloomsbury"),
                Genre.FANTASY,
                cal.getTime()
        );
    }

    private Book getTheLastLecture(Calendar cal) {
        cal.set(2008, Calendar.APRIL, 8);
        return new Book("The Last Lecture",
                new Author("Randy Pausch"),
                new Publication("Hyperion"),
                Genre.NON_FICTION,
                cal.getTime()
        );
    }

}
