package br.com.pirelli.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pirelli.model.Toner;
import br.com.pirelli.repository.Toners;
import br.com.pirelli.service.exception.TonerJaCadastradoException;

@Service
public class CadastroTonerService 
{
	@Autowired
	private Toners toners;
	
	public Toner salvar(Toner toner)
	{
		Optional<Toner> optional = toners.findByModeloAndMarca(toner.getModelo(), toner.getMarca());
		if(optional.isPresent() && toner.getCodigo() == null)
		{
			throw new TonerJaCadastradoException("Toner já cadastrado");
		}
		return toners.saveAndFlush(toner);
	}

}
