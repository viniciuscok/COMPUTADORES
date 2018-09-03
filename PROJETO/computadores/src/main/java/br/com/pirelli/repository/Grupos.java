package br.com.pirelli.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.pirelli.model.Grupo;

public interface Grupos extends JpaRepository<Grupo, Long>
{
	
	Optional<Grupo> findByNomeStartingWithIgnoreCase(String nome);
	
	@Query(value = "SELECT * FROM Grupo WHERE nome like ?1% order by nome ASC", 
			countQuery = "SELECT count(*) FROM Grupo WHERE nome like ?1% order by nome ASC",
		    nativeQuery = true)
	Page<Grupo> findByNome(String nome, Pageable pageable);
	
	@Query(value= "select * from grupo where codigo != 1",
		nativeQuery= true)
	public List<Grupo> buscarGruposSemSistema();

}
