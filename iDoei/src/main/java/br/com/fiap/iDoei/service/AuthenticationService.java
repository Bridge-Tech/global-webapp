package br.com.fiap.iDoei.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import br.com.fiap.iDoei.model.Person;
import br.com.fiap.iDoei.repository.PersonRepository;

@Service
public class AuthenticationService implements UserDetailsService{

	@Autowired
	private PersonRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Person> person = repository.findByUser(username);
		if(person.isEmpty()) 
			throw new UsernameNotFoundException("person not found");
		return person.get();
	}
	public static PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

}		
