package br.com.pirelli.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="os_impressora")
@DiscriminatorValue("IMPRESSORA")
public class OsImpressora extends Manutencao
{

	private static final long serialVersionUID = 1L;
	
	private Impressora impressora;

	@NotNull(message="A impressora deve ser informada")
	@ManyToOne
	@JoinColumn(name="codigo_impressora")
	public Impressora getImpressora() {
		return impressora;
	}

	public void setImpressora(Impressora impressora) {
		this.impressora = impressora;
	}
	
	
	@Transient
	public boolean isNova()
	{
		return this.getCodigo() == null;
	}
	

}
