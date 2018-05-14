package br.com.pirelli.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pirelli.model.Marca;
import br.com.pirelli.repository.Marcas;
import br.com.pirelli.service.exception.ImpossivelExcluirMarcaException;
import br.com.pirelli.service.exception.MarcaJaCadastradaException;

@Service
public class CadastroMarcaService 
{
	@Autowired
	private Marcas marcas;
	
	public Marca salvar(Marca marca)
	{
		Optional<Marca> verificarNome = marcas.findByNomeStartingWithIgnoreCase(marca.getNome());
		
		if(marca.getCodigo() == null)
		{	
			if(verificarNome.isPresent())
			{
				throw new MarcaJaCadastradaException("Marca já cadastrada.");
			}
		}
		return marcas.saveAndFlush(marca);
	}
	
	@Transactional
	public void excluir(Marca marca)
	{
		try
		{
			marcas.delete(marca);
			marcas.flush();
		}catch(RuntimeException e)
		{
			throw new ImpossivelExcluirMarcaException("A marca está sendo usada em outro cadastro.");
		}
	}

}
