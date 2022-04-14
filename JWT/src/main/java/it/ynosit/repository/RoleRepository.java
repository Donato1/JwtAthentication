package it.ynosit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.ynosit.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Role findByName(String name);
}
