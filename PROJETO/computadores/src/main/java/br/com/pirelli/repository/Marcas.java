package br.com.pirelli.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pirelli.model.Marca;

@Repository
public interface Marcas extends JpaRepository<Marca, Long>
{
	public Optional<Marca> findByNomeStartingWithIgnoreCase(String nome);
}
