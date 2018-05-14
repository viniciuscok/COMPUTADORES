package br.com.pirelli.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pirelli.model.Setor;
import br.com.pirelli.repository.Setores;
import br.com.pirelli.service.exception.ImpossivelExcluirSetorException;
import br.com.pirelli.service.exception.SetorJaCadastradoException;

@Service
public class CadastroSetorService 
{
	
	@Autowired
	private Setores setores;
	
	public Setor salvar(Setor setor)
	{
		Optional<Setor> optional = setores.findByNomeStartingWithIgnoreCase(setor.getNome());
		if(optional.isPresent() && setor.getCodigo() == null)
		{
			throw new SetorJaCadastradoException("Setor já cadastrado.");
		}
		
		return setores.saveAndFlush(setor);
	}
	
	@Transactional
	public void excluir(Setor setor)
	{
		try
		{
			setores.delete(setor);
			setores.flush();
		}catch(RuntimeException e)
		{
			throw new ImpossivelExcluirSetorException("O setor não pode ser excluído, pois está sendo usado em outro cadastro.");
		}
	}

}
