package br.com.pirelli.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pirelli.model.Filial;
import br.com.pirelli.repository.Filiais;
import br.com.pirelli.service.exception.ImpossivelExcluirFilialException;
import br.com.pirelli.service.exception.NomeFilialJaCadastradoException;

@Service
public class CadastroFilialService
{
	@Autowired
	private Filiais filiais;
	
	@Transactional
	public Filial salvar(Filial filial)
	{
		Optional<Filial> verificarNome = filiais.findByNomeStartingWithIgnoreCase(filial.getNome());
		
		if(filial.getCodigo() == null)
		{
			if(verificarNome.isPresent())
			{
				throw new NomeFilialJaCadastradoException("Filial já cadastrada.");
			}
			
		}
		
		
		return filiais.save(filial);
	}
	
	@Transactional
	public void excluir(Filial filial)
	{
		try
		{
			filiais.delete(filial);
			filiais.flush();
		}catch(RuntimeException e)
		{
			throw new ImpossivelExcluirFilialException("A filial não pode ser  excluída, pois está sendo usada em "
					+ "outro cadastro, favor remover o cadastro para excluir a filial "+filial.getNome());
		}
	}

}
