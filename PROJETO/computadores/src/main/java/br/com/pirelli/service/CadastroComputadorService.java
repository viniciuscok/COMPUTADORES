package br.com.pirelli.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pirelli.model.Computador;
import br.com.pirelli.model.Status;
import br.com.pirelli.repository.Computadores;
import br.com.pirelli.service.exception.ComputadorJaCadastradoException;
import br.com.pirelli.service.exception.ImpossivelExcluirComputadorException;

@Service
public class CadastroComputadorService 
{
	@Autowired
	private Computadores computadores;
	
	@Transactional
	public Computador salvar(Computador computador)
	{
		//Long total = computadores.countByNome(computador.getNome());
		//Long total2 = computadores.countByStatus(Status.DESATIVADO);
		
		//if(total2 )
		//{
		//	throw new ComputadorJaCadastradoException("Computador já cadastrado");
		//}
		
		//if(total >1 && computador.getCodigo() == null)
		//{
			//throw new ComputadorJaCadastradoException("Computador1 já cadastrado");
		//}
		//Optional<Computador> optional = computadores.findByNomeStartingWithIgnoreCase(computador.getNome());
		
		//Optional<Computador> optional2 = computadores.findByNomeAndStatus(computador.getNome(), Status.DESATIVADO);
		
		//Optional<Computador> optional3 = computadores.findByNomeAndCodigo(computador.getNome(), computador.getCodigo());
		
		List<Computador> listaVerificada = computadores.findByNomeAndStatus(computador.getNome(), computador.getStatus());
		
		
		if((computador.getCodigo() == null) && listaVerificada.size() >= 1)
		{
			throw new ComputadorJaCadastradoException("Computador já cadastrado");
		}
		
		if((computador.getCodigo() != null) && listaVerificada.size() >= 2 && computador.getStatus().equals(Status.ATIVO))
		{
			throw new ComputadorJaCadastradoException("Já existe um computador Ativo com este nome");
		}
		
		
		//if((optional.isPresent() && computador.getCodigo() == null) && !(optional2.isPresent()))
		//{
		//	throw new ComputadorJaCadastradoException("Computador2 já cadastrado");
		//}
		
		//if(computador.getCodigo() != null && !computador.getStatus().getDescricao().equals(Status.DESATIVADO))
		//{
		//	System.out.println("entrou nesse if----------------------");
		//	throw new ComputadorJaCadastradoException("Computador3 já cadastrado");
		//}
		return computadores.save(computador);
	}
	@Transactional
	public void excluir(Computador computador)
	{
		try
		{
			computadores.delete(computador);
			computadores.flush();
		}catch(RuntimeException e)
		{
			throw new ImpossivelExcluirComputadorException("O computador não pode ser excluído, esxite uma máquina ligada ao mesmo");
		}
	}

}
