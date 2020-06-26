package com.mk.webclientservice.model;

import java.util.Date;

public class Book {
    private String name;
    private Author author;
    private Publication publication;
    private Genre genre;
    private Date releasedate;

    public Book() {
    }

    public Book(String name, Author author, Publication publication, Genre genre, Date releasedate) {
        this.name = name;
        this.author = author;
        this.publication = publication;
        this.genre = genre;
        this.releasedate = releasedate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Date getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(Date releasedate) {
        this.releasedate = releasedate;
    }
}
