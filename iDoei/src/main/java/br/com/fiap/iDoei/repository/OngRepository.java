package br.com.fiap.iDoei.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.iDoei.model.Ong;

public interface OngRepository extends JpaRepository<Ong,Long>{

	Optional<Ong> findByCnpj(String cnpj);

}
