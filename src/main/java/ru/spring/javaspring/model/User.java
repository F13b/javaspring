package ru.spring.javaspring.model;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Optional;
import java.util.Set;

@Entity
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Пожалуйста, ведите свой никнейм!")
    @Size(message = "Введите не менее 2 символов!", min=2, max=1000000)
    private String username;

    @NotEmpty(message = "Пожалуйста, ведите свое имя!")
    @Size(message = "Введите не менее 2 символов!", min=2, max=1000000)
    private String name;

    @NotEmpty(message = "Пожалуйста, выберите свой пол!")
    @Size(message = "Введите не менее 2 символов!", min=2, max=1000000)
    private String gender;

    @NotEmpty(message = "Пожалуйста, ведите свой почтовый адрес!")
    @Size(message = "Введите не менее 2 символов!", min=2, max=1000000)
    private String email;

    @NotEmpty(message = "Пожалуйста, введите пароль!")
    @Size(message = "Введите не менее 8 символов!", min=8, max=1000000)
    private String password;

    @Min(message = "Вы еще не родились!", value=0)
    @Max(message = "Люди столько не живут! Черная магия! Колдун дурацкий!", value=110)
    @NotNull(message = "Введите свой возраст!")
    private Integer age;

    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public User() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
