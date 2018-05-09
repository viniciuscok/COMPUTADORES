package br.com.pirelli.repository.helper.toner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.pirelli.filter.TonerFilter;
import br.com.pirelli.model.Toner;

public interface TonersQueries 
{
	public Page<Toner> filtro(TonerFilter tonerFilter, Pageable pageable);

}
