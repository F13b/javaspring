package ru.spring.javaspring.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "book_genre",
               joinColumns = @JoinColumn(name = "genre_id"),
               inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books;

    public Genre(String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }

    public Genre() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
