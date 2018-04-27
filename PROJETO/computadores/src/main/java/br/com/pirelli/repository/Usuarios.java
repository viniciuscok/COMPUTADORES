package br.com.pirelli.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.pirelli.model.Usuario;

@Repository
public interface Usuarios extends JpaRepository<Usuario, Long>
{
	public Optional<Usuario> findByNomeStartingWithIgnoreCase(String nome);
	
	@Query(value = "SELECT * FROM Usuario WHERE nome like ?1% order by nome ASC", 
			countQuery = "SELECT count(*) FROM Usuario WHERE nome like ?1% order by nome ASC",
		    nativeQuery = true)
	Page<Usuario> findByNome(String nome, Pageable pageable);

}
