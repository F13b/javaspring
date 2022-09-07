package ru.spring.javaspring.repo;

import org.springframework.data.repository.CrudRepository;
import ru.spring.javaspring.model.User;
import ru.spring.javaspring.model.Vegetables;

import java.util.List;

public interface VegetablesRepository extends CrudRepository<Vegetables, Long> {
    public List<Vegetables> findByNameContains(String name);
}
