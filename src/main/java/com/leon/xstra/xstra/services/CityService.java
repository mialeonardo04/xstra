package com.leon.xstra.xstra.services;

import com.leon.xstra.xstra.entities.City;
import com.leon.xstra.xstra.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    @Autowired CityRepository cityRepository;

    public CityService() {
    }

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getCities(){
        return cityRepository.findAll();
    }
}
