package br.com.pirelli.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.pirelli.model.Marca;

@Repository
public interface Marcas extends JpaRepository<Marca, Long>
{
	public Optional<Marca> findByNomeStartingWithIgnoreCase(String nome);
	
	@Query(value="SELECT * FROM Marca WHERE nome like ?1% order by nome ASC", 
			countQuery="SELECT count(*) FROM Marca WHERE nome like ?1% order by nome ASC", 
			nativeQuery=true)
	Page<Marca> findByNome(String nome, Pageable pageable);
}
