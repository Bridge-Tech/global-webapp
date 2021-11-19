package br.com.fiap.iDoei.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.iDoei.model.Donation;

public interface DonationRepository extends JpaRepository<Donation, Long>{

}
