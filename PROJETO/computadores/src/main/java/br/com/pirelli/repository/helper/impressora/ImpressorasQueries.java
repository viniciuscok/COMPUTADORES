package br.com.pirelli.repository.helper.impressora;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.pirelli.filter.ImpressoraFilter;
import br.com.pirelli.model.Impressora;

public interface ImpressorasQueries 
{
	public Page<Impressora> filtro(ImpressoraFilter impressoraFilter, Pageable pageable);

}
