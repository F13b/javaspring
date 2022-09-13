package ru.spring.javaspring.repo;

import org.springframework.data.repository.CrudRepository;
import ru.spring.javaspring.model.Town;

public interface TownRepository extends CrudRepository<Town, Long> {
    Town findByTown(String name);
}
