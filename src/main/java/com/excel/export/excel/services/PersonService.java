/*
package com.excel.export.excel.services;

import com.excel.export.excel.entities.Person;
import com.speedment.jpastreamer.application.JPAStreamer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Component
public class PersonService {

    @Autowired
    private JPAStreamer jpaStreamer;

    public List<Person> getPersonsWithName(String name) {
        List<Person> personList = jpaStreamer.stream(Person.class)
                .filter(person -> Boolean.parseBoolean(person.getNom()))
                .skip(10)
                .limit(5)
                .collect(Collectors.toList());

        return personList;
    }
}
*/
