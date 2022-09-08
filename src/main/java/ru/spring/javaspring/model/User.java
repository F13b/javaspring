package ru.spring.javaspring.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.util.Optional;

@Entity
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotEmpty(message = "Пожалуйста, ведите свое имя!")
    @Size(message = "Введите не менее 2 символов!", min=2, max=1000000)
    String name;

    @NotEmpty(message = "Пожалуйста, ведите свой никнейм!")
    @Size(message = "Введите не менее 2 символов!", min=2, max=1000000)
    String nickname;

    @NotEmpty(message = "Пожалуйста, ведите свой почтовый адрес!")
    @Size(message = "Введите не менее 2 символов!", min=2, max=1000000)
    String email;

    @NotEmpty(message = "Пожалуйста, выберите свой пол!")
    @Size(message = "Введите не менее 2 символов!", min=2, max=1000000)
    String gender;

    @Min(message = "Вы еще не родились!", value=0)
    @Max(message = "Люди столько не живут! Черная магия! Колдун дурацкий!", value=110)
    @NotNull(message = "Введите свой возраст!")
    Integer age;

    public User(String name, String nickname, String email, String gender, Integer age) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.gender = gender;
        this.age = age;
    }

    public User() {
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
