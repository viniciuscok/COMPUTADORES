package br.com.pirelli.repository.helper.maquina;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.pirelli.filter.MaquinaFilter;
import br.com.pirelli.model.Maquina;

public interface MaquinasQueries 
{
	public Page<Maquina> filtro(MaquinaFilter maquinaFilter, Pageable pageable);

}
