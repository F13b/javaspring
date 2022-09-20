package ru.spring.javaspring.repo;

import org.springframework.data.repository.CrudRepository;
import ru.spring.javaspring.model.Abonement;

import java.util.List;

public interface AbonementRepository extends CrudRepository<Abonement, Long> {
    List<Abonement> findByNumber(String number);
    Abonement findAbonementByNumber(String number);
}
