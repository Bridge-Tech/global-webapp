package br.com.fiap.iDoei.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.iDoei.model.Food;

public interface FoodRepository extends JpaRepository<Food,Long>{

	//List<Food> findByisSelectedTrueAndPersonId(Long id);
	
	
	Optional<Food> findByIsSelected(Boolean boo);

}
