package ru.spring.javaspring.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Entity
@Table(name = "age_limit")
public class Limit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Min(message = "Возрастное ограничение не может быть меньше 0!", value = 0)
    private String ageLimit;

    @OneToMany(mappedBy = "book_age_limit", fetch = FetchType.EAGER)
    private Collection<Book> books;

    public Limit(String age_limit, Collection<Book> books) {
        this.ageLimit = age_limit;
        this.books = books;
    }

    public Limit() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAge_limit() {
        return ageLimit;
    }

    public void setAge_limit(String age_limit) {
        this.ageLimit = age_limit;
    }

    public Collection<Book> getBooks() {
        return books;
    }

    public void setBooks(Collection<Book> books) {
        this.books = books;
    }
}
