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
	
	public Optional<Computador> findByNomeStartingWithIgnoreCase(String nome);

	@Query(value="select count(*) from computador where tipo_computador = 'notebook'" , 
			nativeQuery=true)
	public Long buscarNotebook();
	
	@Query(value="select count(*) from computador where tipo_computador = 'desktop'" , 
			nativeQuery=true)
	public Long buscarDesktop();
	
	@Query(value="select count(*) from computador where tipo_computador = 'touch'" , 
			nativeQuery=true)
	public Long buscarTouch();

}
