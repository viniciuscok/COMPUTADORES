package br.com.pirelli.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pirelli.model.Computador;
import br.com.pirelli.repository.Computadores;
import br.com.pirelli.service.exception.ComputadorJaCadastradoException;

@Service
public class CadastroComputadorService 
{
	@Autowired
	private Computadores computadores;
	
	
	public Computador salvar(Computador computador)
	{
		Optional<Computador> optional = computadores.findByNomeStartingWithIgnoreCase(computador.getNome());
		
		if(optional.isPresent() && computador.getCodigo() == null)
		{
			throw new ComputadorJaCadastradoException("Computador já cadastrado");
		}
		
		return computadores.save(computador);
	}

}
