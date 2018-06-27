package br.com.pirelli.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pirelli.model.Os;
import br.com.pirelli.repository.OsServicos;

@Service
public class CadastroOsService 
{
	@Autowired
	private OsServicos osServicos;
	
	public Os salvar(Os os)
	{
		return osServicos.save(os);
	}

}
