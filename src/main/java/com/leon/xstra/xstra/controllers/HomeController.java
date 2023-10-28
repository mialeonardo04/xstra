package com.leon.xstra.xstra.controllers;

import com.leon.xstra.xstra.entities.City;
import com.leon.xstra.xstra.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
