package br.com.pirelli.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pirelli.model.Impressora;
import br.com.pirelli.repository.Impressoras;
import br.com.pirelli.service.exception.ImpressoraJaExisteException;

@Service
public class CadastroImpressoraService 
{
	
	@Autowired
	private Impressoras impressoras;
	
	public Impressora salvar(Impressora impressora)
	{
		Optional<Impressora> optional = impressoras.findByEnderecoIPStartingWithIgnoreCase(impressora.getEnderecoIP());
		if(optional.isPresent())
		{
			throw new ImpressoraJaExisteException("Impressora já cadastrada");
		}
		
		return impressoras.saveAndFlush(impressora);
	}

}
