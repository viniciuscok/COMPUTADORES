package br.com.pirelli.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pirelli.model.Permissao;
import br.com.pirelli.repository.Permissoes;
import br.com.pirelli.service.exception.ImpossivelExcluirFilialException;
import br.com.pirelli.service.exception.ImpossivelExcluirPermissaoException;
import br.com.pirelli.service.exception.NomePermissaoJaCadastradoException;

@Service
public class CadastroPermissaoService 
{
	
	@Autowired
	private Permissoes permissoes;
	
	public Permissao salvar(Permissao permissao)
	{
		Optional<Permissao> nomePresente = permissoes.findByNomeStartingWithIgnoreCase(permissao.getNome());
		
		if(permissao.getCodigo() == null)
		{
			if(nomePresente.isPresent())
			{
				throw new NomePermissaoJaCadastradoException("Permissão já cadastrada");
			}
			
		}
		
		return permissoes.save(permissao);
	}

	@Transactional
	public void excluir(Permissao permissao) 
	{
		try
		{
			permissoes.delete(permissao);
			permissoes.flush();
		}catch(RuntimeException e)
		{
			throw new ImpossivelExcluirPermissaoException("A permissão não pode ser  excluída, pois está sendo usada em "
					+ "outro cadastro, favor remover o cadastro para excluir a permissão ");
		}
		
	}

}
