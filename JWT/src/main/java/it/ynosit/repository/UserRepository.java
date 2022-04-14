package it.ynosit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.ynosit.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
}
