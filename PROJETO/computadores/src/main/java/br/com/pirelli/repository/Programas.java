package br.com.pirelli.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.pirelli.model.Programa;

@Repository
public interface Programas extends JpaRepository<Programa, Long>
{
	public Optional<Programa> findByNomeStartingWithIgnoreCase(String nome);
	
	@Query(value="SELECT * FROM Programa WHERE nome like ?1% order by nome ASC", 
			countQuery="SELECT count(*) FROM Programa WHERE nome like ?1% order by nome ASC",
			nativeQuery=true)
	Page<Programa> findByNome(String nome, Pageable pageable);

}
