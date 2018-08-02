package br.com.pirelli.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;

import br.com.pirelli.model.Computador;
import br.com.pirelli.model.Status;
import br.com.pirelli.repository.helper.computador.ComputadoresQueries;

@Controller
public interface Computadores extends JpaRepository<Computador, Long>, ComputadoresQueries
{
	
	public Optional<Computador> findByNomeStartingWithIgnoreCase(String nome);
	
	//public Optional<Computador> findByNomeAndStatus(String nome, Status status);
	
	//public Optional<Computador> findByNomeAndCodigo(String nome, Long codigo);
	
	//public Long countByNome(String nome);
	
	//public Long countByStatus(Status status);

	@Query(value="select count(*) from computador where tipo_computador = 'notebook'" , 
			nativeQuery=true)
	public Long buscarNotebook();
	
	@Query(value="select count(*) from computador where tipo_computador = 'desktop'" , 
			nativeQuery=true)
	public Long buscarDesktop();
	
	@Query(value="select count(*) from computador where tipo_computador = 'touch'" , 
			nativeQuery=true)
	public Long buscarTouch();
	
	@Query(value="select * from equipamento e inner join computador c on e.codigo = c.codigo where e.tipo_equipamento = 'computador' order by(c.nome) asc",
			nativeQuery=true)
	public List<Computador> computadorOrdemCrescente();

}
