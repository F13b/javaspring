package ru.spring.javaspring.repo;

import org.springframework.data.repository.CrudRepository;
import ru.spring.javaspring.model.Publisher;

import java.util.List;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
    List<Publisher> findByName(String name);
}
