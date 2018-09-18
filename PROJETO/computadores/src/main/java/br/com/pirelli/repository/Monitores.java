package br.com.pirelli.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.pirelli.model.Computador;
import br.com.pirelli.model.Marca;
import br.com.pirelli.model.Monitor;
import br.com.pirelli.repository.helper.monitor.MonitoresQueries;

@Repository
public interface Monitores extends JpaRepository<Monitor, Long>, MonitoresQueries
{
	public Optional<Monitor> findBynumeroDeSerieAndMarca(String numeroDeSerie, Marca marca);
	
	@Query(value="select * from equipamento e inner join monitor m on e.codigo = m.codigo where e.tipo_equipamento = 'monitor' order by(m.numero_serie) asc",
			nativeQuery=true)
	public List<Monitor> monitorOrdemCrescente();
}
