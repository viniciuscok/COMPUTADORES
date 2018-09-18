package br.com.pirelli.filter;

import br.com.pirelli.model.Marca;
import br.com.pirelli.model.Modelo;
import br.com.pirelli.model.Setor;
import br.com.pirelli.model.Status;

public class MonitorFilter 
{
	private Long codigo;
	private String numeroDeSerie;
	private Modelo modelo;
	private Status status;
	private Marca marca;
	private Setor setor;
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNumeroDeSerie() {
		return numeroDeSerie;
	}
	public void setNumeroDeSerie(String numeroDeSerie) {
		this.numeroDeSerie = numeroDeSerie;
	}
	
	public Modelo getModelo() {
		return modelo;
	}
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Marca getMarca() {
		return marca;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	public Setor getSetor() {
		return setor;
	}
	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	
	

}
