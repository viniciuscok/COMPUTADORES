package br.com.pirelli.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="monitor")
@DiscriminatorValue("MONITOR")
public class Monitor extends Equipamento
{

	private static final long serialVersionUID = 1L;
	
	private String numeroDeSerie;
	private String bemPatrimonial;

	@Column(name="numero_serie", nullable = false, unique=false)
	public String getNumeroDeSerie() {
		return numeroDeSerie;
	}

	public void setNumeroDeSerie(String numeroDeSerie) {
		this.numeroDeSerie = numeroDeSerie;
	}
	
	
	@Column(name="bem_patrimonial", nullable = false, unique=false)
	public String getBemPatrimonial() {
		return bemPatrimonial;
	}

	public void setBemPatrimonial(String bemPatrimonial) {
		this.bemPatrimonial = bemPatrimonial;
	}

	@Transient
	public boolean isNovo()
	{
		return this.getCodigo() == null;
	}
}
