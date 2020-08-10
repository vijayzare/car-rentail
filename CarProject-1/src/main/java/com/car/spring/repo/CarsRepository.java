package com.car.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car.spring.entity.Car;

public interface CarsRepository extends JpaRepository<Car, Long>{

}
