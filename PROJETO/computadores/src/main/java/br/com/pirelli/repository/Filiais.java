package br.com.pirelli.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.pirelli.model.Filial;

@Repository
public interface Filiais extends JpaRepository<Filial, Long>
{
	public Optional<Filial> findByNomeStartingWithIgnoreCase(String nome);
	
	@Query(value = "SELECT * FROM Filial WHERE Nome = ?1",
		    countQuery = "SELECT count(*) FROM Filial WHERE NOME = ?1",
		    nativeQuery = true)
		  Page<Filial> findByNome(String nome, Pageable pageable);
		

}
