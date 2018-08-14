package br.com.pirelli.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="os_computador")
@DiscriminatorValue("COMPUTADOR")
public class OsComputador extends Manutencao
{
	
	private static final long serialVersionUID = 1L;
	
	private Computador computador;
	
	@NotNull(message="O computador deve ser informado.")
	@ManyToOne
	@JoinColumn(name="codigo_computador")
	public Computador getComputador() {
		return computador;
	}
	public void setComputador(Computador computador) {
		this.computador = computador;
	}
	
	@Transient
	public boolean isNova()
	{
		return this.getCodigo() == null;
	}
	
	

}
