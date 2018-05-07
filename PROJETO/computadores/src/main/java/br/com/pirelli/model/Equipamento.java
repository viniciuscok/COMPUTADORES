package br.com.pirelli.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_equipamento", discriminatorType= DiscriminatorType.STRING)
public abstract class Equipamento implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	private Long codigo;
	//private String numeroDeSerie;
	private String enderecoIP;
	private String obs;
	private Status status;
	private Filial filial;
	private Modelo modelo;
	private Marca marca;
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
	
	//@NotBlank(message="O número de série deve ser informado")
	//@Column(name="numero_serie", nullable = false, unique=false)
	//public String getnumeroDeSerie() {
	//	return numeroDeSerie;
	//}
	//public void setnumeroDeSerie(String numeroDeSerie) {
	//	this.numeroDeSerie = numeroDeSerie;
	//}
	
	@Column(name="endereco_ip", nullable=true, unique=false)
	public String getEnderecoIP() {
		return enderecoIP;
	}
	public void setEnderecoIP(String enderecoIP) {
		this.enderecoIP = enderecoIP;
	}
	
	@Column(name="obs")
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="status", nullable=false, unique=false)
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	@NotNull(message="A filial deve ser informada")
	@ManyToOne
	@JoinColumn(name="codigo_filial", nullable=false, unique=false)
	public Filial getFilial() {
		return filial;
	}
	public void setFilial(Filial filial) {
		this.filial = filial;
	}
	
	@NotNull(message="O modelo deve ser informado")
	@ManyToOne
	@JoinColumn(name="codigo_modelo", nullable=false, unique=false)
	public Modelo getModelo() {
		return modelo;
	}
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
	
	@NotNull(message="A marca deve ser informada")
	@ManyToOne
	@JoinColumn(name="codigo_marca", nullable=false, unique=false)
	public Marca getMarca() {
		return marca;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	
	@NotNull(message="O setor deve ser informado")
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
		Equipamento other = (Equipamento) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
	
}
