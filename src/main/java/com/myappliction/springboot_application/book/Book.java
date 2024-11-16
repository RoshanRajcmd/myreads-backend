package com.myappliction.springboot_application.book;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Book {
    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    private Long bookId;
    private String title;
    private String summary;
    private LocalDate publishedOn;
    private String author;

    public Book() {
    }

    public Book(Long bookId, String title, String summary, LocalDate publishedOn, String author) {
        this.bookId = bookId;
        this.title = title;
        this.summary = summary;
        this.publishedOn = publishedOn;
        this.author = author;
    }

    public Book(String title, String summary, LocalDate publishedOn, String author) {
        this.title = title;
        this.summary = summary;
        this.publishedOn = publishedOn;
        this.author = author;
    }

    public Long getId() {
        return bookId;
    }

    public void setId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public LocalDate getPublishedOn() {
        return publishedOn;
    }

    public void setPublishedOn(LocalDate publishedOn) {
        this.publishedOn = publishedOn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
