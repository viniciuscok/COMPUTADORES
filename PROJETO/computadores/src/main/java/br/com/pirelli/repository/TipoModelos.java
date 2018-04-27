package br.com.pirelli.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.pirelli.model.TipoModelo;

@Repository
public interface TipoModelos extends JpaRepository<TipoModelo, Long>
{
	public Optional<TipoModelo> findByNomeStartingWithIgnoreCase(String nome);
	
	@Query(value="SELECT * FROM Tipo_Modelo WHERE nome like ?1% order by nome ASC", 
			countQuery="SELECT count(*) FROM Tipo_Modelo WHERE nome like ?1% order by nome ASC",
			nativeQuery=true)
	Page<TipoModelo> findByNome(String nome, Pageable pageable);
}
