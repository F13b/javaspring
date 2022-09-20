package ru.spring.javaspring.repo;

import org.springframework.data.repository.CrudRepository;
import ru.spring.javaspring.model.Sheet;

public interface SheetRepository extends CrudRepository<Sheet, Long> {
}
