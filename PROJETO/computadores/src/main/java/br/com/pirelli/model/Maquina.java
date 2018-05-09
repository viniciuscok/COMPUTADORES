package br.com.pirelli.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.lang.NonNull;

@Entity
@Table(name="maquina")
public class Maquina implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Long codigo;
	private String nome;
	private String sigma;
	private String iniMaquina;
	private String obs;
	private Computador computador;
	private Impressora impressora;
	private Setor setor;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo", nullable=false, unique=true)
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	@NotBlank(message="O nome da maquina deve ser informado.")
	@Column(name="nome", nullable=false, unique=true)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(name="sigma", nullable=true, unique=true)
	public String getSigma() {
		return sigma;
	}
	public void setSigma(String sigma) {
		this.sigma = sigma;
	}
	
	@Column(name="ini_maquina", nullable=true, unique=false)
	public String getIniMaquina() {
		return iniMaquina;
	}
	public void setIniMaquina(String iniMaquina) {
		this.iniMaquina = iniMaquina;
	}
	
	@Column(name="obs")
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	
	@NotNull(message="O computador deve ser informado")
	@OneToOne
	@JoinColumn(name="codigo_computador", nullable=false, unique=false)
	public Computador getComputador() {
		return computador;
	}
	public void setComputador(Computador computador) {
		this.computador = computador;
	}
	
	@OneToOne
	@JoinColumn(name="codigo_impressora", nullable=true, unique=false)
	public Impressora getImpressora() {
		return impressora;
	}
	public void setImpressora(Impressora impressora) {
		this.impressora = impressora;
	}
	
	@NotNull(message="O setor deve ser informado.")
	@ManyToOne
	@JoinColumn(name="codigo_setor", nullable=false, unique=false)
	public Setor getSetor() {
		return setor;
	}
	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Maquina other = (Maquina) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	@Transient
	public boolean isNova()
	{
		return this.getCodigo() == null;
	}
	

}
