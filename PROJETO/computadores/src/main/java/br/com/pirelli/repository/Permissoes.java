package br.com.pirelli.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pirelli.model.Permissao;

@Repository
public interface Permissoes extends JpaRepository<Permissao, Long> 
{
	Optional<Permissao> findByNomeStartingWithIgnoreCase(String nome);

}
