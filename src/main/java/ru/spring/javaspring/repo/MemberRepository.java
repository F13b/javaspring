package ru.spring.javaspring.repo;

import org.springframework.data.repository.CrudRepository;
import ru.spring.javaspring.model.Member;

public interface MemberRepository extends CrudRepository<Member, Long> {
    Member findByUsername(String surname);
}
