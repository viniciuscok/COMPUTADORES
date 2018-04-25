package br.com.pirelli.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pirelli.model.Filial;
import br.com.pirelli.repository.Filiais;
import br.com.pirelli.service.exception.NomeFilialJaCadastradoException;

@Service
public class CadastroFilialService
{
	@Autowired
	private Filiais filiais;
	
	public Filial salvar(Filial filial)
	{
		Optional<Filial> optional = filiais.findByNomeStartingWithIgnoreCase(filial.getNome());
		if(optional.isPresent())
		{
			throw new NomeFilialJaCadastradoException("Filial já cadastrada");
		}
		
		return filiais.saveAndFlush(filial);
	}

}
