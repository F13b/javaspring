package ru.spring.javaspring.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "edition")
public class Edition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    @OneToMany(mappedBy = "edition", fetch = FetchType.EAGER)
    private Collection<Book> books;

    public Edition(String type, Collection<Book> books) {
        this.type = type;
        this.books = books;
    }

    public Edition() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Collection<Book> getBooks() {
        return books;
    }

    public void setBooks(Collection<Book> books) {
        this.books = books;
    }
}
