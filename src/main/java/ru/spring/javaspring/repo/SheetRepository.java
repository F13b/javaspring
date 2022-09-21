package ru.spring.javaspring.repo;

import org.springframework.data.repository.CrudRepository;
import ru.spring.javaspring.model.Book;
import ru.spring.javaspring.model.Sheet;

import java.util.List;

public interface SheetRepository extends CrudRepository<Sheet, Long> {

}
