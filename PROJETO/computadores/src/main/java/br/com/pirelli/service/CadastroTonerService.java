package br.com.pirelli.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pirelli.model.Toner;
import br.com.pirelli.repository.Toners;

@Service
public class CadastroTonerService 
{
	@Autowired
	private Toners toners;
	
	public Toner salvar(Toner toner)
	{
		return toners.saveAndFlush(toner);
	}

}
