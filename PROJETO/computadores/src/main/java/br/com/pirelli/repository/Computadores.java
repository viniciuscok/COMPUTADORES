package br.com.pirelli.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

import br.com.pirelli.model.Computador;

@Controller
public interface Computadores extends JpaRepository<Computador, Long>
{
	
	public Optional<Computador> findByNomeStartingWithIgnoreCase(String nome);

}
