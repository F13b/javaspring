package ru.spring.javaspring.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String surname;
    private String name;
    private String secondname;
    private String birth;
    private String passport;

    private boolean active;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "abonement_id")
    private Abonement abonement;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToOne(optional = true, mappedBy = "member")
    private Sheet sheet;

    public Member(String username, String password, String surname, String name, String secondname, String birth, String passport, boolean active, Abonement abonement, Set<Role> roles, Sheet sheet) {
        this.username = username;
        this.password = password;
        this.surname = surname;
        this.name = name;
        this.secondname = secondname;
        this.birth = birth;
        this.passport = passport;
        this.active = active;
        this.abonement = abonement;
        this.roles = roles;
        this.sheet = sheet;
    }

    public Member() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Abonement getAbonement() {
        return abonement;
    }

    public void setAbonement(Abonement abonement) {
        this.abonement = abonement;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }
}
