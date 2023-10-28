package com.leon.xstra.xstra.repositories;

import com.leon.xstra.xstra.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City,Long> {
    List<City> findByCityNameContaining(String cityname);

}
