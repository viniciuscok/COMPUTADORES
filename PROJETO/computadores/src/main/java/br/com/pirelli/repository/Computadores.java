package br.com.pirelli.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

import br.com.pirelli.model.Computador;
import br.com.pirelli.repository.helper.computador.ComputadoresQueries;

@Controller
public interface Computadores extends JpaRepository<Computador, Long>, ComputadoresQueries
{
	
	public Optional<Computador> findByNomeStartingWithIgnoreCase(String nome);
	
	
	
	//= tp.codigo where tp.nome = 'COMPUTADOR'"

}
