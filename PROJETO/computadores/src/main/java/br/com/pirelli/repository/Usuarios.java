package br.com.pirelli.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pirelli.model.Usuario;

@Repository
public interface Usuarios extends JpaRepository<Usuario, Long>
{
	public Optional<Usuario> findByNomeStartingWithIgnoreCase(String nome);

}
