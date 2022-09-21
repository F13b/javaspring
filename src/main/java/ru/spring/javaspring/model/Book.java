package ru.spring.javaspring.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer pages;
    private Double price;
    private String year;
    private Integer amount;

    @ManyToMany
    @JoinTable(name = "book_author",
               joinColumns = @JoinColumn(name = "book_id"),
               inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;

    @ManyToMany
    @JoinTable(name = "book_branch",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "branch_id"))
    private List<Branch> branches;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Edition edition;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Publisher publisher;

    @ManyToMany
    @JoinTable(name = "book_genre",
               joinColumns = @JoinColumn(name = "book_id"),
               inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Limit book_age_limit;

    @OneToOne(optional = true, mappedBy = "book")
    private Sheet sheet;


    public Book(String name, Integer pages, Double price, String year, Integer amount, List<Author> authors, List<Branch> branches, Edition edition, Publisher publisher, List<Genre> genres, Limit book_age_limit, Sheet sheet) {
        this.name = name;
        this.pages = pages;
        this.price = price;
        this.year = year;
        this.amount = amount;
        this.authors = authors;
        this.branches = branches;
        this.edition = edition;
        this.publisher = publisher;
        this.genres = genres;
        this.book_age_limit = book_age_limit;
        this.sheet = sheet;
    }

    public Book() {
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

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    public Edition getEdition() {
        return edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public Limit getBook_age_limit() {
        return book_age_limit;
    }

    public void setBook_age_limit(Limit book_age_limit) {
        this.book_age_limit = book_age_limit;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }
}
