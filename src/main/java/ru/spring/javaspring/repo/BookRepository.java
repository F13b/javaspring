package ru.spring.javaspring.repo;

import org.springframework.data.repository.CrudRepository;
import ru.spring.javaspring.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
    Book findByName(String name);
}
