package br.com.pirelli.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pirelli.model.Computador;
import br.com.pirelli.model.Monitor;
import br.com.pirelli.repository.Monitores;
import br.com.pirelli.service.exception.ImpossivelExcluirComputadorException;
import br.com.pirelli.service.exception.ImpossivelExcluirMonitorException;
import br.com.pirelli.service.exception.MonitorJaCadastradoException;

@Service
public class CadastroMonitorService 
{
	@Autowired
	private Monitores monitores;

	@Transactional
	public Monitor salvar(Monitor monitor)
	{
		Optional<Monitor> verificarNumeroDeSerie = monitores.findBynumeroDeSerieAndMarca(monitor.getNumeroDeSerie(), monitor.getMarca());
		if(monitor.getCodigo() == null && verificarNumeroDeSerie.isPresent())
		{
			throw new MonitorJaCadastradoException("Monitor já Cadastrado");
		}
		
		return monitores.save(monitor);
	}
	
	@Transactional
	public void excluir(Monitor monitor)
	{
		try
		{
			monitores.delete(monitor);
			monitores.flush();
		}catch(RuntimeException e)
		{
			throw new ImpossivelExcluirMonitorException("O monitor não pode ser excluído");
		}
	}

	
}
