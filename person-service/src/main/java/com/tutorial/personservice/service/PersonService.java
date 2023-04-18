package com.tutorial.personservice.service;

import com.tutorial.personservice.entity.Person;
import com.tutorial.personservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> getAll() {
        return personRepository.findAll();
    }
    public Person getPersonById(int id) {
        return personRepository.findById(id).orElse((null));
    }
    public Person save(Person person) {
        Person personNew = personRepository.save(person);
        return personNew;
    }
}
