package br.com.pirelli.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pirelli.model.Marca;
import br.com.pirelli.model.Monitor;
import br.com.pirelli.repository.helper.monitor.MonitoresQueries;

@Repository
public interface Monitores extends JpaRepository<Monitor, Long>, MonitoresQueries
{
	public Optional<Monitor> findBynumeroDeSerieAndMarca(String numeroDeSerie, Marca marca);
}
