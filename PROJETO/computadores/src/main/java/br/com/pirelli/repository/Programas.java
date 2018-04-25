package br.com.pirelli.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pirelli.model.Programa;

@Repository
public interface Programas extends JpaRepository<Programa, Long>
{
	public Optional<Programa> findByNomeStartingWithIgnoreCase(String nome);

}
