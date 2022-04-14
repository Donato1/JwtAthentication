package it.ynosit.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.ynosit.entity.Role;
import it.ynosit.entity.User;
import it.ynosit.repository.RoleRepository;
import it.ynosit.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUsername(username);
		if(user == null) {
			
			throw new UsernameNotFoundException(username);
		}else {
			
		}
		
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(role -> {authorities.add(new SimpleGrantedAuthority(role.getName()));
		});
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
	}
	/*
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> user = userRepository.findByUsername(username);
		
		if(user.isPresent()) {
			log.info("Utente creato" + user.get());
			return (UserDetails) user.get();
		}
		throw new UsernameNotFoundException(username);
	}*/
	
	public User saveUser(User user) {
		log.info("Salva un nuovo utente {} nel database", user.getName());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	public Role saveRole(Role role) {
		log.info("Salva un nuovo ruolo {} nel database", role.getName());
		return roleRepository.save(role);
	}
	
	public void addRoleToUser(String username, String roleName) {
		
		log.info("Aggiungo il ruolo {} all'utente {}", roleName, username);
		
		User user = userRepository.findByUsername(username);
		Role role = roleRepository.findByName(roleName);
		
		user.getRoles().add(role);
	}
	
	public User getUser(String username) {
		
		log.info("Recupero l'utente {}", username);
		
		return userRepository.findByUsername(username);
	}
	
	public List<User> getUsers(){
		
		log.info("Recupero tutti gli utenti");
		
		return userRepository.findAll();
	}

}
