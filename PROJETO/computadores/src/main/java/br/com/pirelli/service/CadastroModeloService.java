package br.com.pirelli.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pirelli.model.Modelo;
import br.com.pirelli.repository.Modelos;
import br.com.pirelli.service.exception.ModeloJacadastradoException;

@Service
public class CadastroModeloService 
{
	@Autowired
	private Modelos modelos;
	
	public Modelo salvar(Modelo modelo)
	{
		Optional<Modelo> optional = modelos.findByNomeAndTipoModelo(modelo.getNome(), modelo.getTipoModelo());
		
		if(optional.isPresent() && modelo.getCodigo() == null)
		{
			throw new ModeloJacadastradoException("Modelo já cadastrado para esse equipamento");
		}
		
		return modelos.save(modelo);
	}

}
