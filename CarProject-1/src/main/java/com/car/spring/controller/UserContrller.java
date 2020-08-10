package com.car.spring.controller;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.aspectj.apache.bcel.classfile.Module.Uses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.car.spring.entity.User;
import com.car.spring.exception.NotFoundException;
import com.car.spring.repo.UserRepository;

@RestController
@RequestMapping("/api/v1")
public class UserContrller {

	@Autowired
	private UserRepository userRepository;

	@PutMapping("/{id}")
	public User updateUser(@PathVariable Long id, @RequestBody User updateUser) {
		if (!userRepository.existsById(id)) {
			throw new NotFoundException("User Not Found");
		}
		return userRepository.findById(id).map(user -> {
			user.setName(updateUser.getName());
			user.setEmail(updateUser.getEmail());
			// user.setCreatedTime(Instant.now());
			user.setPhone(updateUser.getPhone());
			user.setUpdated(Instant.now());
			return userRepository.save(user);
		}).orElseThrow(() -> new NotFoundException("user not Found with   " + id));
	}

	@GetMapping("{id}")
	public Optional<User> getUserById(@PathVariable Long id) {
		return userRepository.findById(id);
	}

	@PostMapping()
	public String postUsers(@RequestBody User users) {
		userRepository.save(users);
		return users.getName() + "   Added sucesssfully";
	}

	@GetMapping()
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@DeleteMapping("{id}")
	public List<User> deleteUseByid(@PathVariable Long id) {
		userRepository.deleteById(id);
		return userRepository.findAll();
	}
}