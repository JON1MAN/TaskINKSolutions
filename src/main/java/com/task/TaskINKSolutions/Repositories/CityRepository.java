package com.task.TaskINKSolutions.Repositories;

import com.task.TaskINKSolutions.Entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long>{
    Optional<City> findCityById(Long id);
    Optional<City> findCityByName(String name);

    @Query(value = "SELECT * FROM cities c WHERE c.name = :cityName AND c.state_id = (SELECT state_id FROM states WHERE name = :stateName) LIMIT 1", nativeQuery = true)
    City findFirstCityByNameAndState(@Param("cityName") String cityName, @Param("stateName") String stateName);

}
