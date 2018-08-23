package br.com.pirelli.repository.helper.login;

import java.util.List;

import br.com.pirelli.filter.LoginFilter;
import br.com.pirelli.model.Login;

public interface LoginsQueries
{
	public List<Login> filtrar(LoginFilter loginFilter);
	
	public Login buscarComGrupos(Long codigo);

}
