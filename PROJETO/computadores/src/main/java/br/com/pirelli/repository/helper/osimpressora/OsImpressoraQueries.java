package br.com.pirelli.repository.helper.osimpressora;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.pirelli.filter.OsImpressoraFilter;
import br.com.pirelli.model.OsImpressora;

public interface OsImpressoraQueries 
{
	
	public Page<OsImpressora> filtro(OsImpressoraFilter osImpressoraFilter, Pageable pageable);

}
