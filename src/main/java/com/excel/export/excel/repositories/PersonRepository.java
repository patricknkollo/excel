package com.excel.export.excel.repositories;

import com.excel.export.excel.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface PersonRepository extends JpaRepository<Person, Long> {
}
