package com.tutorial.personservice.service;

import com.tutorial.personservice.entity.Person;
import com.tutorial.personservice.feignclients.BikeFeignClient;
import com.tutorial.personservice.feignclients.CarFeignClient;
import com.tutorial.personservice.model.Bike;
import com.tutorial.personservice.model.Car;
import com.tutorial.personservice.repository.PersonRepository;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import org.springframework.web.client.RestTemplate;
@Service
public class PersonService {
    
    @Autowired
    CarFeignClient carFeignClient;

    @Autowired
    BikeFeignClient bikeFeignClient;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RestTemplate restTemplate;

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
    public List<Car> getCards(int personId){
        List<Car> cars = restTemplate.getForObject(
                "http://localhost:8002/car/byperson/" +
                        personId, List.class);
        return cars;
    }
    public List<Bike> getBikes(int personId){
        List<Bike> bikes = restTemplate.getForObject(
                "http://localhost:8003/bike/byperson/" +
                        personId, List.class);
        return bikes;
    }
    //Llamamos al metodo CarFeignClient @PostMapping
    public Car saveCar(int personId, Car car){
        car.setPersonId(personId);
        Car carNew = carFeignClient.save(car);
        return carNew;
    }
    public Bike saveBike(int personId, Bike bike){
    bike.setPersonId(personId);
    Bike bikeNew = bikeFeignClient.save(bike);
    return bikeNew;
    }
    
    public Map<String, Object> getPersonAndVehicles(int personId) {
    Map<String, Object> result = new HashMap<>();
    Person person = personRepository.findById(personId).orElse(null);
    if(person == null) {
        result.put("Mensaje", "no existe el person");
        return result;
    }
    result.put("Person", person);
    List<Car> cars = carFeignClient.getCars(personId);
    if(cars.isEmpty())
        result.put("Cars", "ese person no tiene coches");
    else
        result.put("Cars", cars);
    List<Bike> bikes = bikeFeignClient.getBikes(personId);
    if(bikes.isEmpty())
        result.put("Bikes", "ese person no tiene motos");
    else
        result.put("Bikes", bikes);
    return result;
    }
}
