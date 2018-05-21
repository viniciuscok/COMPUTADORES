package br.com.pirelli.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pirelli.model.Permissao;
import br.com.pirelli.repository.Permissoes;
import br.com.pirelli.service.exception.NomePermissaoJaCadastradoException;

@Service
public class CadastroPermissaoService 
{
	
	@Autowired
	private Permissoes permissoes;
	
	public Permissao salvar(Permissao permissao)
	{
		Optional<Permissao> nomePresente = permissoes.findByNomeStartingWithIgnoreCase(permissao.getNome());
		
		if(nomePresente.isPresent())
		{
			throw new NomePermissaoJaCadastradoException("Permissão já cadastrada");
		}
		
		return permissoes.save(permissao);
	}

}
