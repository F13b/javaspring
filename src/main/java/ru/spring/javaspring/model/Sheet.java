package ru.spring.javaspring.model;

import javax.persistence.*;

@Entity
@Table(name = "sheet")
public class Sheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date_of_issue;
    private String return_date;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Book book;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Member member;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Sheet(String date_of_issue, String return_date, Book book, Member member, Employee employee) {
        this.date_of_issue = date_of_issue;
        this.return_date = return_date;
        this.book = book;
        this.member = member;
        this.employee = employee;
    }

    public Sheet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate_of_issue() {
        return date_of_issue;
    }

    public void setDate_of_issue(String date_of_issue) {
        this.date_of_issue = date_of_issue;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
