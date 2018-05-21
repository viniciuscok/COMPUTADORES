package br.com.pirelli.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pirelli.model.Grupo;
import br.com.pirelli.repository.Grupos;
import br.com.pirelli.service.exception.ImpossivelExcluirFilialException;
import br.com.pirelli.service.exception.ImpossivelExcluirGrupoException;
import br.com.pirelli.service.exception.NomegrupoJaCadastradoException;

@Service
public class CadastroGrupoService 
{
	@Autowired
	private Grupos grupos;
	
	public Grupo salvar(Grupo grupo)
	{
		Optional<Grupo> verificarNome = grupos.findByNomeStartingWithIgnoreCase(grupo.getNome());
		
		if(grupo.getCodigo() == null)
		{
			if(verificarNome.isPresent())
			{
				throw new NomegrupoJaCadastradoException("Grupo já cadastrado.");
			}
			
		}
		
		return grupos.save(grupo);
	}

	@Transactional
	public void excluir(Grupo grupo) 
	{
		try
		{
			grupos.delete(grupo);
			grupos.flush();
		}catch(RuntimeException e)
		{
			throw new ImpossivelExcluirGrupoException("O grupo não pode ser  excluído, pois está sendo usado em "
					+ "outro cadastro, favor remover o cadastro para excluir o grupo ");
		}
		
	}
}
