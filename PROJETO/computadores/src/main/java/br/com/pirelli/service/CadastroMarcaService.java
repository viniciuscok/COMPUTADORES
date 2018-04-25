package br.com.pirelli.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pirelli.model.Marca;
import br.com.pirelli.repository.Marcas;
import br.com.pirelli.service.exception.MarcaJaCadastradaException;

@Service
public class CadastroMarcaService 
{
	@Autowired
	private Marcas marcas;
	
	public Marca salvar(Marca marca)
	{
		Optional<Marca> optional = marcas.findByNomeStartingWithIgnoreCase(marca.getNome());
		if(optional.isPresent())
		{
			throw new MarcaJaCadastradaException("Marca já cadastrada");
		}
		
		return marcas.saveAndFlush(marca);
	}

}
