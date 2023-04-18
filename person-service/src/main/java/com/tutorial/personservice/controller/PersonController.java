package com.tutorial.personservice.controller;

import com.tutorial.personservice.entity.Person;
import com.tutorial.personservice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonService personService;

    @GetMapping
    public ResponseEntity<List<Person>> getAll(){
        List<Person> person= personService.getAll();
        if(person.isEmpty()){
            return ResponseEntity.noContent().build();
        }return ResponseEntity.ok(person);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getById(@PathVariable("id") int id ){
        Person person = personService.getPersonById(id);
        if(person == null){
            return ResponseEntity.notFound().build();
        }return ResponseEntity.ok(person);
    }
    @PostMapping()
    public ResponseEntity<Person> save(@RequestBody Person person){
        Person personNew = personService.save(person);
        return ResponseEntity.ok(personNew);
    }
}
