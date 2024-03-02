package com.devsuperior.dslearn.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslearn.dto.UserDTO;
import com.devsuperior.dslearn.entities.User;
import com.devsuperior.dslearn.repositories.UserRepository;
import com.devsuperior.dslearn.services.execeptions.ResourceNotFoundException;

@Service
public class UserService implements UserDetailsService { //UserDetailsService interface para SpringSecuitiry Autentication

	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	//dependecias
	@Autowired
	private UserRepository repository;
	
	//endpoints
	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		Optional<User> obj = repository.findById(id);
		User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not Found"));
		return new UserDTO(entity);
	}
	
	//metodo do UserDetailsService
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByEmail(username);
		if (user == null) {
			logger.error("User Not Found: " + username);
			throw new UsernameNotFoundException("Email Not Found!");
		}
		logger.info("User Found: " + username);
		return user;
	}
}