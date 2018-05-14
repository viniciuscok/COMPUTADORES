package br.com.pirelli.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pirelli.model.Maquina;
import br.com.pirelli.repository.Maquinas;
import br.com.pirelli.service.exception.ImpossivelExcluirMaquinaException;
import br.com.pirelli.service.exception.MaquinaJaCadastradaException;
import br.com.pirelli.service.exception.SigmaMaquinaJaCadastradaException;

@Service
public class CadastroMaquinaService 
{
	@Autowired
	private Maquinas maquinas;
	
	@Transactional
	public Maquina salvar(Maquina maquina)
	{
		Optional<Maquina> verificarNome = maquinas.findByNomeStartingWithIgnoreCase(maquina.getNome());
		Optional<Maquina> verificarSigma = maquinas.findBySigmaStartingWithIgnoreCase(maquina.getSigma());
		if(maquina.getCodigo() == null)
		{
			if(verificarNome.isPresent())
			{
				throw new MaquinaJaCadastradaException("Maquina já cadastrada.");
			}
			if(verificarSigma.isPresent())
			{
				throw new SigmaMaquinaJaCadastradaException("O sigma da máquina informado já foi cadastrado.");
			}
			
		}
		
		return maquinas.save(maquina);
	}
	
	@Transactional
	public void excluir(Maquina maquina)
	{
		try
		{
			maquinas.delete(maquina);
			maquinas.flush();
		}catch(RuntimeException e)
		{
			throw new ImpossivelExcluirMaquinaException("A maquina cadastrada em outro setor");
		}
	}

}
