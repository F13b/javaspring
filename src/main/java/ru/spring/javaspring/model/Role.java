package ru.spring.javaspring.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;

public enum Role implements GrantedAuthority {
    USER, ADMIN, LIBRARIAN;

    @Override
    public String getAuthority() {return name();}
}
