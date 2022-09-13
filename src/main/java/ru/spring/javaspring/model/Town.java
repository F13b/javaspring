package ru.spring.javaspring.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String town;

    @ManyToMany
    @JoinTable (name = "user_town",
                joinColumns = @JoinColumn (name = "town_id"),
                inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
