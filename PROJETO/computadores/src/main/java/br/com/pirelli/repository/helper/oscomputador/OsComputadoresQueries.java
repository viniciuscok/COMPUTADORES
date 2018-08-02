package br.com.pirelli.repository.helper.oscomputador;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.pirelli.filter.OsComputadorFilter;
import br.com.pirelli.model.OsComputador;

public interface OsComputadoresQueries 
{
	public Page<OsComputador> filtro(OsComputadorFilter osComputadorFilter, Pageable pageable);

}
