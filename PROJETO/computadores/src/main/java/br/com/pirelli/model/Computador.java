package br.com.pirelli.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="computador")
@DiscriminatorValue("COMPUTADOR")
public class Computador extends Equipamento
{
	
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String memoria;
	private String hd;
	private String processador;
	private String code;
	private String bemPatrimonial;
	private String numeroDeSerie;
	private So sistemaOperacional;
	private TipoComputador tipoComputador;
	private Usuario usuario;
	private List<Programa> programas;
	private List<Impressora> impressoras;
	
	
	@NotBlank(message="O computador deve ser informado")
	@Column(name="nome", nullable=false, unique=false)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(name="memoria", nullable=true, unique=false)
	public String getMemoria() {
		return memoria;
	}
	public void setMemoria(String memoria) {
		this.memoria = memoria;
	}
	
	@Column(name="hd", nullable=true, unique=false)
	public String getHd() {
		return hd;
	}
	public void setHd(String hd) {
		this.hd = hd;
	}
	
	@Column(name="processador", nullable=true, unique=false)
	public String getProcessador() {
		return processador;
	}
	public void setProcessador(String processador) {
		this.processador = processador;
	}
	
	@Column(name="code", nullable=true, unique=false)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(name="bem_patrimonial", nullable=true, unique=false)
	public String getBemPatrimonial() {
		return bemPatrimonial;
	}
	public void setBemPatrimonial(String bemPatrimonial) {
		this.bemPatrimonial = bemPatrimonial;
	}
	
	//@NotBlank(message="O número de série deve ser informado")
	@Column(name="numero_serie", nullable = false, unique=false)
	public String getNumeroDeSerie() {
		return numeroDeSerie;
	}
	public void setNumeroDeSerie(String numeroDeSerie) {
		this.numeroDeSerie = numeroDeSerie;
	}
	
	@NotNull(message="O sistema operacional deve ser informado.")
	@Enumerated(EnumType.STRING)
	@Column(name="sistema_operacional", nullable=true)
	public So getSistemaOperacional() {
		return sistemaOperacional;
	}
	public void setSistemaOperacional(So sistemaOperacional) {
		this.sistemaOperacional = sistemaOperacional;
	}
	
	@NotNull(message="O tipo do computador deve ser informado.")
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_computador", nullable=false)
	public TipoComputador getTipoComputador() {
		return tipoComputador;
	}
	public void setTipoComputador(TipoComputador tipoComputador) {
		this.tipoComputador = tipoComputador;
	}
	
	@NotNull(message="O usuario deve ser informado")
	@ManyToOne
	@JoinColumn(name="codigo_usuario", nullable=false, unique=false)
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@ManyToMany
	@JoinTable(name = "computador_programa", joinColumns = @JoinColumn(name = "codigo_computador")
				, inverseJoinColumns = @JoinColumn(name = "codigo_programa"))	
	public List<Programa> getProgramas() {
		return programas;
	}
	public void setProgramas(List<Programa> programas) {
		this.programas = programas;
	}
	
	@ManyToMany
	@JoinTable(name="computador_impressora", joinColumns = @JoinColumn(name = "codigo_computador")
				, inverseJoinColumns = @JoinColumn(name = "codigo_impressora"))
	public List<Impressora> getImpressoras() {
		return impressoras;
	}
	public void setImpressoras(List<Impressora> impressoras) {
		this.impressoras = impressoras;
	}
	
	@Transient
	public boolean isNovo()
	{
		return this.getCodigo() == null;
	}
	
	
	

}
