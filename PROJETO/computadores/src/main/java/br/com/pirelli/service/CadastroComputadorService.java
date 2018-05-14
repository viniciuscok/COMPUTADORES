package br.com.pirelli.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pirelli.model.Computador;
import br.com.pirelli.repository.Computadores;
import br.com.pirelli.service.exception.ComputadorJaCadastradoException;
import br.com.pirelli.service.exception.ImpossivelExcluirComputadorException;

@Service
public class CadastroComputadorService 
{
	@Autowired
	private Computadores computadores;
	
	@Transactional
	public Computador salvar(Computador computador)
	{
		Optional<Computador> optional = computadores.findByNomeStartingWithIgnoreCase(computador.getNome());
		
		if(optional.isPresent() && computador.getCodigo() == null)
		{
			throw new ComputadorJaCadastradoException("Computador já cadastrado");
		}
		
		return computadores.save(computador);
	}
	@Transactional
	public void excluir(Computador computador)
	{
		try
		{
			computadores.delete(computador);
			computadores.flush();
		}catch(RuntimeException e)
		{
			throw new ImpossivelExcluirComputadorException("O computador não pode ser excluído, esxite uma máquina ligada ao mesmo");
		}
	}

}
