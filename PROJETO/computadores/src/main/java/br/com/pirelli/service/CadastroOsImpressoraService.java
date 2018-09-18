package br.com.pirelli.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pirelli.model.Login;
import br.com.pirelli.model.OsImpressora;
import br.com.pirelli.model.Status;
import br.com.pirelli.model.StatusManutencao;
import br.com.pirelli.repository.Logins;
import br.com.pirelli.repository.OsImpressoras;
import br.com.pirelli.service.exception.SolucaoManutencaoException;
import br.com.pirelli.service.exception.StatusManutencaoException;

@Service
public class CadastroOsImpressoraService 
{
	@Autowired
	private OsImpressoras osImpressoras;
	
	@Autowired
	private Logins logins;
	
	@Transactional
	public OsImpressora salvar(OsImpressora osImpressora)
	{
		Login emailUsuarioEntrada = logins.buscarCodigoPorEmail(osImpressora.UsuarioAutenticado());
		if(osImpressora.getCodigo() == null)
		{
			
			osImpressora.setLoginEntrada(emailUsuarioEntrada);
			
			osImpressora.setStatusManutencao(StatusManutencao.ABERTO);
			osImpressora.getImpressora().setStatus(Status.MANUTENCAO);
			
			osImpressora.getImpressora().getSetor().setNome(osImpressora.getImpressora().getSetor().getNome());
		}
		
		if(osImpressora.getCodigo() != null)
		{
			osImpressora.setLoginEntrada(emailUsuarioEntrada);
			if(!osImpressora.getSolucao().isEmpty() && StatusManutencao.ABERTO.equals(osImpressora.getStatusManutencao()))
			{
				throw new StatusManutencaoException("Favor alterar o status da Manutenção");
			}
			
			else if(osImpressora.getSolucao().isEmpty() && StatusManutencao.FECHADO.equals(osImpressora.getStatusManutencao()))
			{
				throw new SolucaoManutencaoException("Favor informar a solução do problema.");
			}
			
			else if(osImpressora.getSolucao().isEmpty() && StatusManutencao.SEMSOLUCAO.equals(osImpressora.getStatusManutencao()))
			{
				throw new SolucaoManutencaoException("Favor informar a solução do problema.");
			}
			
			else if(StatusManutencao.FECHADO.equals(osImpressora.getStatusManutencao()))
			{
				//buscar usuário logado no sistema
				Login emailusuarioSaida = logins.buscarCodigoPorEmail(osImpressora.UsuarioAutenticado());
				
				osImpressora.setDataFechamento(LocalDateTime.now());
				osImpressora.setLoginSaida(emailusuarioSaida);
				osImpressora.getImpressora().setStatus(Status.ATIVO);
				
				//System.out.println("Login entrada" + osComputador.getLoginEntrada()+"\n\nLogin saída"+osComputador.getLoginSaida());
			}
			
			else if(StatusManutencao.SEMSOLUCAO.equals(osImpressora.getStatusManutencao()))
			{
				Login emailusuarioSaida = logins.buscarCodigoPorEmail(osImpressora.UsuarioAutenticado());
				//osComputador.getLoginEntrada();
				osImpressora.setDataFechamento(LocalDateTime.now());
				osImpressora.setLoginSaida(emailusuarioSaida);
				//osComputador.setLoginEntrada(osComputador.getLoginEntrada());
				osImpressora.getImpressora().setStatus(Status.DESATIVADO);
			}	
			
		}
		
		//osComputador.setLoginEntrada(osComputador.getLoginEntrada());
		return osImpressoras.save(osImpressora);
	}

}
