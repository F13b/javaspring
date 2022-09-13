package ru.spring.javaspring.repo;

import org.springframework.data.repository.CrudRepository;
import ru.spring.javaspring.model.News;
import ru.spring.javaspring.model.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByName(String name);
    public List<User> findByNameContains(String name);
    public List<User> findByUsernameContains(String nickname);

    User findByUsername(String login);
}
