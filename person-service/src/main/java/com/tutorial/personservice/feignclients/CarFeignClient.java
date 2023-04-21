/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tutorial.personservice.feignclients;

import com.tutorial.personservice.model.Car;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Albert
 */
@FeignClient(name="car-service", url = "http://localhost:8002")
public interface CarFeignClient {
    @PostMapping()
    Car save(@RequestBody Car car);
    @GetMapping("/byperson/{personId}")
    List<Car> getCars(@PathVariable("personId") int personId);
}
