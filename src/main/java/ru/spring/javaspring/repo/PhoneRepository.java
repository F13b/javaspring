package ru.spring.javaspring.repo;

import org.springframework.data.repository.CrudRepository;
import ru.spring.javaspring.model.Phone;

public interface PhoneRepository extends CrudRepository<Phone, Long> {
    Phone findByNumber(String number);
}
