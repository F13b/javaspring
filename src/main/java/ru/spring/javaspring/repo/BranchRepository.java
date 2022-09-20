package ru.spring.javaspring.repo;

import org.springframework.data.repository.CrudRepository;
import ru.spring.javaspring.model.Branch;

import java.util.List;

public interface BranchRepository extends CrudRepository<Branch, Long> {
    List<Branch> findByAddress(String address);
}
