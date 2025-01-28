package com.opt.entity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.opt.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public Optional<User> findByUserName(String userName);

	public Optional<User> findByEmail(String email);

}
