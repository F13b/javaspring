package ru.spring.javaspring.repo;

import org.springframework.data.repository.CrudRepository;
import ru.spring.javaspring.model.Post;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findByNameContains(String name);
}
