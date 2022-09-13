package ru.spring.javaspring.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
public class Birth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String birth;

    @OneToMany(mappedBy = "date_of_birth", fetch = FetchType.EAGER)
    private Collection<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}
