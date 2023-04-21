/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tutorial.personservice.feignclients;

import com.tutorial.personservice.model.Bike;
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
@FeignClient(name="bike-service", url = "http://localhost:8003")
public interface BikeFeignClient {
    @PostMapping()
    public Bike save(@RequestBody Bike bike);
    @GetMapping("/byperson/{personId}")
    List<Bike> getBikes(@PathVariable("personId") int personId);
}
