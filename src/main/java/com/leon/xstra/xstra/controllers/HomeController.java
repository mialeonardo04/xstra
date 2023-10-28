package com.leon.xstra.xstra.controllers;

import com.leon.xstra.xstra.entities.City;
import com.leon.xstra.xstra.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HomeController {

    @Autowired CityService cityService;

    @GetMapping("sayHello")
    public String sayHello(){
        return "Hello User";
    }

    @GetMapping("city/all")
    public List<City> getCities(){
        return cityService.getCities();
    }

    @PostMapping("city/add")
    public City SaveCity(@RequestBody City city){
        return cityService.saveCity(city);
    }

    @GetMapping("city/{cityname}")
    public List<City> getCity(@PathVariable String cityname){
        return cityService.getCity(cityname);
    }

}
