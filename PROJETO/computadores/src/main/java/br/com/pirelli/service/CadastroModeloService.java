package br.com.pirelli.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pirelli.model.Modelo;
import br.com.pirelli.repository.Modelos;
import br.com.pirelli.service.exception.ImpossivelEcluirModeloException;
import br.com.pirelli.service.exception.ModeloJacadastradoException;

@Service
public class CadastroModeloService 
{
	@Autowired
	private Modelos modelos;
	
	@Transactional
	public Modelo salvar(Modelo modelo)
	{
		Optional<Modelo> optional = modelos.findByNomeAndTipoModelo(modelo.getNome(), modelo.getTipoModelo());
		
		if(optional.isPresent() && modelo.getCodigo() == null)
		{
			throw new ModeloJacadastradoException("Modelo já cadastrado para essa Categoria.");
		}
		
		return modelos.save(modelo);
	}
	
	@Transactional
	public void excluir(Modelo modelo)
	{
		try
		{
			modelos.delete(modelo);
			modelos.flush();
		}catch(RuntimeException e)
		{
			throw new ImpossivelEcluirModeloException("O modelo não pode ser excluído, pois está sendo usado em outro cadastro.");
		}
	}

}
