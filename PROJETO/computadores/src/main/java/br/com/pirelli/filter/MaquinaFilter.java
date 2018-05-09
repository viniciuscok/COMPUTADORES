package br.com.pirelli.filter;

import br.com.pirelli.model.Computador;
import br.com.pirelli.model.Setor;

public class MaquinaFilter 
{
	private Long codigo;
	private String nome;
	private String sigma;
	private Computador computador;
	private Setor setor;
	
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSigma() {
		return sigma;
	}
	public void setSigma(String sigma) {
		this.sigma = sigma;
	}
	public Computador getComputador() {
		return computador;
	}
	public void setComputador(Computador computador) {
		this.computador = computador;
	}
	public Setor getSetor() {
		return setor;
	}
	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	
	
	

}
