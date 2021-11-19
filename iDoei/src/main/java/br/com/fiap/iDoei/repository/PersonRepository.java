package br.com.fiap.iDoei.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.iDoei.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

	Optional<Person> findBycpf(String cpf);

	Optional<Person> findByUser(String username);



}
