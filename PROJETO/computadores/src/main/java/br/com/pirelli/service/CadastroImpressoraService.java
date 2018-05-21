package br.com.pirelli.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;

import br.com.pirelli.model.Impressora;
import br.com.pirelli.repository.Impressoras;
import br.com.pirelli.service.exception.ImpossivelExcluirImpressora;
import br.com.pirelli.service.exception.ImpressoraJaExisteException;

@Service
public class CadastroImpressoraService 
{
	
	@Autowired
	private Impressoras impressoras;
	
	public Impressora salvar(Impressora impressora)
	{
		//Optional<Impressora> optional = impressoras.findByEnderecoIPStartingWithIgnoreCase(impressora.getEnderecoIP());
		//if(optional.isPresent())
		//{
		//	throw new ImpressoraJaExisteException("Impressora já cadastrada");
		//}
		
		return impressoras.saveAndFlush(impressora);
	}

	@Transactional
	public void excluir(Impressora impressora) 
	{
		try
		{
			impressoras.delete(impressora);
			impressoras.flush();
		}catch(RuntimeException e)
		{
			throw new ImpossivelExcluirImpressora("A impressora não pode ser excluída, favor verificar se a mesma está sento utilizada em outro cadastro");
		}
		
	}

}
