package ru.spring.javaspring.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "branch")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;

    @ManyToMany
    @JoinTable(name = "book_branch",
               joinColumns = @JoinColumn(name = "branch_id"),
               inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books;

    public Branch(String address, List<Book> books) {
        this.address = address;
        this.books = books;
    }

    public Branch() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
