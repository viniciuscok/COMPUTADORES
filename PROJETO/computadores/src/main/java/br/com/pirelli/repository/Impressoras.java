package br.com.pirelli.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pirelli.model.Impressora;

@Repository
public interface Impressoras extends JpaRepository<Impressora, Long>
{
	public Optional<Impressora> findByEnderecoIPStartingWithIgnoreCase(String enderecoIP);

}
