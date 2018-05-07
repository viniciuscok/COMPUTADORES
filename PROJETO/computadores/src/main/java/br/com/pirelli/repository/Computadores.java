package br.com.pirelli.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;

import br.com.pirelli.model.Computador;
import br.com.pirelli.repository.helper.computador.ComputadoresQueries;

@Controller
public interface Computadores extends JpaRepository<Computador, Long>, ComputadoresQueries
{
	//String nomePC = "touch";
	public Optional<Computador> findByNomeStartingWithIgnoreCase(String nome);

	//@Query(value="SELECT nome,codigo FROM Computador where tipo_computador=nomePC", 
	//		nativeQuery=true)
	//public List<Computador> buscarComputador();

}
