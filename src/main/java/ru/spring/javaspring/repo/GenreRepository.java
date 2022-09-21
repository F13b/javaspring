package ru.spring.javaspring.repo;

import org.springframework.data.repository.CrudRepository;
import ru.spring.javaspring.model.Genre;

import java.util.List;

public interface GenreRepository extends CrudRepository<Genre, Long> {
    List<Genre> findByName(String name);
}
