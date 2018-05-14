package br.com.pirelli.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pirelli.model.Toner;
import br.com.pirelli.repository.Toners;
import br.com.pirelli.service.exception.ImpossivelExcluirTonerException;
import br.com.pirelli.service.exception.TonerJaCadastradoException;

@Service
public class CadastroTonerService 
{
	@Autowired
	private Toners toners;
	
	public Toner salvar(Toner toner)
	{
		Optional<Toner> optional = toners.findByModeloAndMarca(toner.getModelo(), toner.getMarca());
		if(optional.isPresent() && toner.getCodigo() == null)
		{
			throw new TonerJaCadastradoException("Toner já cadastrado");
		}
		return toners.saveAndFlush(toner);
	}

	@Transactional
	public void excluir(Toner toner) 
	{
		try
		{
			toners.delete(toner);
			toners.flush();
		}catch(RuntimeException e)
		{
			throw new ImpossivelExcluirTonerException("O tner não pode ser excluído, pois existe uma impressora cadastrada com esse toner.");
		}
		
	}

}
