package ru.spring.javaspring.repo;

import org.springframework.data.repository.CrudRepository;
import ru.spring.javaspring.model.Birth;

public interface BirthRepository extends CrudRepository<Birth, Long> {
    Birth findByBirth(String name);
}
