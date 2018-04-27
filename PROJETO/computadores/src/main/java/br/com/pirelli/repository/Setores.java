package br.com.pirelli.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.pirelli.model.Setor;

@Repository
public interface Setores extends JpaRepository<Setor, Long>
{
	public Optional<Setor> findByNomeStartingWithIgnoreCase(String nome);
	
	@Query(value = "SELECT * FROM Setor WHERE nome like ?1% order by nome ASC", 
			countQuery = "SELECT count(*) FROM Setor WHERE nome like ?1% order by nome ASC",
		    nativeQuery = true)
	Page<Setor> findByNome(String nome, Pageable pageable);
}
