package br.com.pirelli.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pirelli.model.Maquina;
import br.com.pirelli.repository.Maquinas;
import br.com.pirelli.service.exception.MaquinaJaCadastradaException;

@Service
public class CadastroMaquinaService 
{
	@Autowired
	private Maquinas maquinas;
	
	@Transactional
	public Maquina salvar(Maquina maquina)
	{
		Optional<Maquina> optional = maquinas.findByNomeStartingWithIgnoreCase(maquina.getNome());
		if(optional.isPresent())
		{
			throw new MaquinaJaCadastradaException("Maquina já cadastrada");
		}
		return maquinas.save(maquina);
	}

}
