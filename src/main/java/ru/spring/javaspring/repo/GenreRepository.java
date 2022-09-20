package ru.spring.javaspring.repo;

import org.springframework.data.repository.CrudRepository;
import ru.spring.javaspring.model.Genre;

public interface GenreRepository extends CrudRepository<Genre, Long> {
}
