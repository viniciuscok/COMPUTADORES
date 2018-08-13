package br.com.pirelli.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import br.com.pirelli.model.Login;
import br.com.pirelli.model.OsComputador;
import br.com.pirelli.model.Status;
import br.com.pirelli.model.StatusManutencao;
import br.com.pirelli.repository.Logins;
import br.com.pirelli.repository.OsComputadores;
import br.com.pirelli.service.exception.SolucaoManutencaoException;


@Service
public class CadastroOsComputadorService 
{
	@Autowired
	private OsComputadores osComputadores;
	
	@Autowired
	private Logins logins;
	
	@Transactional
	public OsComputador salvar(OsComputador osComputador)
	{
		//Login emailUsuarioEntrada = logins.buscarCodigoPorEmail(osComputador.UsuarioAutenticado());
		//Login emailUsuarioEntrada =null;
		//Login emailusuarioSaida = null;
		
		System.out.println("teste teste teste de variavel" + osComputador.getLoginEntrada());
		
		if(osComputador.getCodigo() == null)
		{
			Login emailUsuarioEntrada = logins.buscarCodigoPorEmail(osComputador.UsuarioAutenticado());
			osComputador.setLoginEntrada(emailUsuarioEntrada);
			//osComputador.setLoginSaida(null);
			osComputador.setStatusManutencao(StatusManutencao.ABERTO);
			osComputador.getComputador().setStatus(Status.MANUTENCAO);
		}
		if(osComputador.getCodigo() != null && osComputador.getSolucao().isEmpty() && StatusManutencao.FECHADO.equals(osComputador.getStatusManutencao()))
		{
			throw new SolucaoManutencaoException("Favor informar a solução do problema.");
		}
		
		if(osComputador.getCodigo() != null && StatusManutencao.FECHADO.equals(osComputador.getStatusManutencao()))
		{
			//buscar usuário logado no sistema
			Login emailusuarioSaida = logins.buscarCodigoPorEmail(osComputador.UsuarioAutenticado());
			
			osComputador.setDataFechamento(LocalDateTime.now());
			osComputador.setLoginSaida(emailusuarioSaida);
			osComputador.getComputador().setStatus(Status.ATIVO);
			
			System.out.println("Login entrada" + osComputador.getLoginEntrada()+"\n\nLogin saída"+osComputador.getLoginSaida());
		}
		
		if(osComputador.getCodigo() != null && StatusManutencao.SEMSOLUCAO.equals(osComputador.getStatusManutencao()))
		{
			Login emailusuarioSaida = logins.buscarCodigoPorEmail(osComputador.UsuarioAutenticado());
			//osComputador.getLoginEntrada();
			osComputador.setDataFechamento(LocalDateTime.now());
			osComputador.setLoginSaida(emailusuarioSaida);
			//osComputador.setLoginEntrada(osComputador.getLoginEntrada());
			osComputador.getComputador().setStatus(Status.DESATIVADO);
		}
		
		//osComputador.setLoginEntrada(osComputador.getLoginEntrada());
		return osComputadores.save(osComputador);
	}

}
