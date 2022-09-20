package ru.spring.javaspring.repo;

import org.springframework.data.repository.CrudRepository;
import ru.spring.javaspring.model.Edition;

public interface EditionRepository extends CrudRepository<Edition, Long> {
}
