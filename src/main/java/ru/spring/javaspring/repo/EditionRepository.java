package ru.spring.javaspring.repo;

import org.springframework.data.repository.CrudRepository;
import ru.spring.javaspring.model.Edition;

import java.util.List;

public interface EditionRepository extends CrudRepository<Edition, Long> {
    List<Edition> findByType(String type);
}
