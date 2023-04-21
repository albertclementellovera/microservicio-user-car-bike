package com.tutorial.personservice.controller;

import com.tutorial.personservice.entity.Person;
import com.tutorial.personservice.model.Bike;
import com.tutorial.personservice.model.Car;
import com.tutorial.personservice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    @GetMapping("/cars/{personId}")
    public ResponseEntity<List<Car>> getCars(@PathVariable("personId") int personId){
        Person person = personService.getPersonById(personId);
        if(person==null)
            return ResponseEntity.notFound().build();
        List<Car> cars = personService.getCards(personId);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/bikes/{personId}")
    public ResponseEntity<List<Bike>> getBikes(@PathVariable("personId") int personId){
        Person person = personService.getPersonById(personId);
        if(person==null)
            return ResponseEntity.notFound().build();
        List<Bike> bikes = personService.getBikes(personId);
        return ResponseEntity.ok(bikes);
    }
    
    @PostMapping("/savecar/{personId}")
    public ResponseEntity<Car> saveCar(
            @PathVariable("personId") int personId,
            @RequestBody Car car){
        if(personService.getPersonById(personId)== null)
           return ResponseEntity.notFound().build();
        Car carNew = personService.saveCar(personId, car);
        return ResponseEntity.ok(car);
    }
    @PostMapping("/savebike/{personId}")
    public ResponseEntity<Bike> saveBike(
        @PathVariable("personId") int personId,
        @RequestBody Bike bike){
        if(personService.getPersonById(personId)== null)
           return ResponseEntity.notFound().build();
    Bike bikeNew = personService.saveBike(personId, bike);
    return ResponseEntity.ok(bikeNew);
    }

    @GetMapping("/getAll/{personId}")
    public ResponseEntity<Map<String, Object>>
    getAllVehicles(@PathVariable("personId") int personId) {
        Map<String, Object> result = 
        personService.getPersonAndVehicles(personId);
        return ResponseEntity.ok(result);
    }
}
