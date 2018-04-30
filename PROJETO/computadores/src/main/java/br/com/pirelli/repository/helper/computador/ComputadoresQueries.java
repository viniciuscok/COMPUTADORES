package br.com.pirelli.repository.helper.computador;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.pirelli.filter.ComputadorFilter;
import br.com.pirelli.model.Computador;

public interface ComputadoresQueries 
{
	public Page<Computador> filtro(ComputadorFilter computadorFilter, Pageable pageable);

}
