package br.com.pirelli.filter;

import br.com.pirelli.model.Computador;
import br.com.pirelli.model.Login;
import br.com.pirelli.model.StatusManutencao;
import br.com.pirelli.model.TipoManutencao;

public class OsComputadorFilter 
{
	private Long codigo;
	private Computador computador;
	private Login loginEntrada;
	private Login loginSaida;
	private TipoManutencao tipoManutencao;
	private StatusManutencao statusManutencao;
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public Computador getComputador() {
		return computador;
	}
	public void setComputador(Computador computador) {
		this.computador = computador;
	}
	public Login getLoginEntrada() {
		return loginEntrada;
	}
	public void setLoginEntrada(Login loginEntrada) {
		this.loginEntrada = loginEntrada;
	}
	public Login getLoginSaida() {
		return loginSaida;
	}
	public void setLoginSaida(Login loginSaida) {
		this.loginSaida = loginSaida;
	}
	public TipoManutencao getTipoManutencao() {
		return tipoManutencao;
	}
	public void setTipoManutencao(TipoManutencao tipoManutencao) {
		this.tipoManutencao = tipoManutencao;
	}
	public StatusManutencao getStatusManutencao() {
		return statusManutencao;
	}
	public void setStatusManutencao(StatusManutencao statusManutencao) {
		this.statusManutencao = statusManutencao;
	}	

}
