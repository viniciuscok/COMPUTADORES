package br.com.pirelli.repository.helper.monitor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.pirelli.filter.MonitorFilter;
import br.com.pirelli.model.Monitor;

public interface MonitoresQueries 
{
	public Page<Monitor> filtro(MonitorFilter monitorFilter, Pageable pageable);

}
