package br.com.pirelli.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pirelli.model.Programa;
import br.com.pirelli.repository.Programas;
import br.com.pirelli.service.exception.ProgramaJaCadastradoException;

@Service
public class CadastroProgramaService 
{
	@Autowired
	private Programas programas;
	
	public Programa salvar(Programa programa)
	{
		Optional<Programa> optional = programas.findByNomeStartingWithIgnoreCase(programa.getNome());
		
		if(optional.isPresent() && programa.getCodigo() == null)
		{
			throw new ProgramaJaCadastradoException("Programa já cadastro");
		}
		
		return programas.saveAndFlush(programa);
	}

}
