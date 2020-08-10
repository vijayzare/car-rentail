package com.car.spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.car.spring.entity.Car;
import com.car.spring.exception.NotFoundException;
import com.car.spring.repo.CarsRepository;
import com.car.spring.repo.UserRepository;

@RestController
@RequestMapping("/api")
public class CrasController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CarsRepository carsRepository;

	@PostMapping("v2/{usrId}/cars")
	public Car PostCars(@PathVariable Long usrId, @RequestBody Car cars) {
		return userRepository.findById(usrId).map(user -> {
			cars.setUser(user);
			return carsRepository.save(cars);
		}).orElseThrow(() -> new com.car.spring.exception.NotFoundException("User not Found"));
	}

//	@PostMapping()
//	public Car postNewCars(@RequestBody Car cars) {
//		return carsRepository.save(cars);
//	}

	@GetMapping("v2/{id}")
	public Optional<Car> carsById(@PathVariable Long id) {
		return carsRepository.findById(id);
	}

	@GetMapping("v2/all")
	public List<Car> getAllCars() {
		return carsRepository.findAll();
	}

	@DeleteMapping("v2/cancle/{id}")
	public List<Car> deleteCrasById(@PathVariable Long id) {
		carsRepository.deleteById(id);
		return carsRepository.findAll();
	}

	@PutMapping("v1/{userId}/cars/{carsId}")
	public Car updateCars(@PathVariable Long userId, @PathVariable Long carsId, @RequestBody Car updarteCras) {
		if (!userRepository.existsById(userId)) {
			throw new NotFoundException("User not found ..");
		}
		return carsRepository.findById(carsId).map(car -> {
			car.setDriverName(updarteCras.getDriverName());
			car.setFuleType(updarteCras.getFuleType());
			car.setNumber(updarteCras.getNumber());
			car.setName(updarteCras.getName());
			car.setSetes(updarteCras.getSetes());
			// car.setUpdated(Instant.now());
			return carsRepository.save(car);

		}).orElseThrow(() -> new NotFoundException(" Car Not Found "));

	}

}
