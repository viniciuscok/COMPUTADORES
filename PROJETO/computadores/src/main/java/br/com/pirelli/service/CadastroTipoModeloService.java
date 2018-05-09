package br.com.pirelli.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pirelli.model.TipoModelo;
import br.com.pirelli.repository.TipoModelos;
import br.com.pirelli.service.exception.TipoModeloJaCadastradoException;

@Service
public class CadastroTipoModeloService 
{
	
	@Autowired
	private TipoModelos tipoModelos;
	
	public TipoModelo salvar(TipoModelo tipoModelo)
	{
		Optional<TipoModelo> optional = tipoModelos.findByNomeStartingWithIgnoreCase(tipoModelo.getNome());
		
		if(optional.isPresent() && tipoModelo.getCodigo() == null)
		{
			throw new TipoModeloJaCadastradoException("Tipo do Modelo já cadastrado");
		}
		
		return tipoModelos.saveAndFlush(tipoModelo);
	}

}
