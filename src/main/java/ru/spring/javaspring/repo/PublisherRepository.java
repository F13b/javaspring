package ru.spring.javaspring.repo;

import org.springframework.data.repository.CrudRepository;
import ru.spring.javaspring.model.Publisher;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
}
