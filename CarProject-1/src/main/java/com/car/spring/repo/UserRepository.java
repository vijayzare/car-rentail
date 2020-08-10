package com.car.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car.spring.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
