/*package br.com.pirelli.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pirelli.model.Login;
import br.com.pirelli.model.Manutencao;
import br.com.pirelli.model.StatusManutencao;
import br.com.pirelli.repository.Logins;
import br.com.pirelli.repository.Manutencoes;
import br.com.pirelli.service.exception.SolucaoManutencaoException;

@Service
public class CadastroManutencaoService 
{
	@Autowired
	private Manutencoes manutencoes;
	
	@Autowired
	private Logins logins;
	
	public Manutencao salvar(Manutencao manutencao)
	{
		if(manutencao.getCodigo() == null)
		{
			Login emailUsuario = logins.buscarCodigoPorEmail(manutencao.UsuarioAutenticado());
			manutencao.setLoginEntrada(emailUsuario);
		}
		if(manutencao.getCodigo() != null && manutencao.getSolucao().isEmpty() && StatusManutencao.FECHADO.equals(manutencao.getStatusManutencao()))
		{
			throw new SolucaoManutencaoException("Favor informar a solução do problema.");
		}
		
		if(manutencao.getCodigo() != null && StatusManutencao.FECHADO.equals(manutencao.getStatusManutencao()))
		{
			Login emailusuario = logins.buscarCodigoPorEmail(manutencao.UsuarioAutenticado());
			manutencao.setLoginSaida(emailusuario);
		}
		
		return manutencoes.save(manutencao);
	}

}*/
