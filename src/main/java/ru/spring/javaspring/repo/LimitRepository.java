package ru.spring.javaspring.repo;

import org.springframework.data.repository.CrudRepository;
import ru.spring.javaspring.model.Limit;

public interface LimitRepository extends CrudRepository<Limit, Long> {
}
