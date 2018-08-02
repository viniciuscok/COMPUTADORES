package br.com.pirelli.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pirelli.model.OsComputador;
import br.com.pirelli.repository.helper.oscomputador.OsComputadoresQueries;

@Repository
public interface OsComputadores extends JpaRepository<OsComputador, Long>, OsComputadoresQueries
{

}
