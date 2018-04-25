package br.com.pirelli.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pirelli.model.TipoModelo;

@Repository
public interface TipoModelos extends JpaRepository<TipoModelo, Long>
{
	public Optional<TipoModelo> findByNomeStartingWithIgnoreCase(String nome);
}
