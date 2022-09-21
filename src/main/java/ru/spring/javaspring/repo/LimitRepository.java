package ru.spring.javaspring.repo;

import org.springframework.data.repository.CrudRepository;
import ru.spring.javaspring.model.Limit;

import java.util.List;

public interface LimitRepository extends CrudRepository<Limit, Long> {
    Limit findByAgeLimit(String age);
}
