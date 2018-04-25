package br.com.pirelli.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pirelli.model.Setor;

@Repository
public interface Setores extends JpaRepository<Setor, Long>
{
	public Optional<Setor> findByNomeStartingWithIgnoreCase(String nome);
}
