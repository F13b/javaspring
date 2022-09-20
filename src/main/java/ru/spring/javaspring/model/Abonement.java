package ru.spring.javaspring.model;

import javax.persistence.*;

@Entity
@Table(name = "abonement")
public class Abonement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String number;
    String date_of_issue;
    String expirtion_date;

    @OneToOne(optional = true, mappedBy = "abonement")
    private Member owner;

    public Abonement(String number, String date_of_issue, String expirtion_date) {
        this.number = number;
        this.date_of_issue = date_of_issue;
        this.expirtion_date = expirtion_date;
    }

    public Abonement() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate_of_issue() {
        return date_of_issue;
    }

    public void setDate_of_issue(String date_of_issue) {
        this.date_of_issue = date_of_issue;
    }

    public String getExpirtion_date() {
        return expirtion_date;
    }

    public void setExpirtion_date(String expirtion_date) {
        this.expirtion_date = expirtion_date;
    }

    public Member getOwner() {
        return owner;
    }

    public void setOwner(Member owner) {
        this.owner = owner;
    }
}
