package br.com.pirelli.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.tomcat.jni.Local;
import org.springframework.security.core.context.SecurityContextHolder;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_equipamento", discriminatorType= DiscriminatorType.STRING)
public abstract class Manutencao implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	private Long codigo;
	private LocalDateTime dataEntrada;
	private LocalDateTime dataFechamento;
	private String descricao;
	//private String equipamento;
	private String solucao;
	private TipoManutencao tipoManutencao;
	private Login loginEntrada;
	private Login loginSaida;
	private StatusManutencao statusManutencao;
	private String obs;
	
	public Manutencao() 
	{
		//LocalDate data = LocalDate.now();
		//LocalTime hora = LocalTime.now();
		statusManutencao = StatusManutencao.ABERTO;
		this.dataEntrada = LocalDateTime.now();
		if(this.codigo != null )
		{
			//this.dataFechamento = LocalDateTime.now(fusoHorarioDeSaoPaulo);
			//this.dataEntrada = this.getDataEntrada();
		}
		//if(UsuarioAutenticado() != null)
		//{
		//	System.out.println("deu certo esta merda");
		//}
		//System.out.println("-----------------------------"+ UsuarioAutenticado());
		
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo", nullable=false, unique=true)
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	//@NotNull(message="A data da entrada deve ser informada")
	@Column(name="data_entrada")
	public LocalDateTime getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(LocalDateTime dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	
	//@NotNull(message="A data de fechamento da os deve ser informada")
	@Column(name="data_fechamento")
	public LocalDateTime getDataFechamento() {
		return dataFechamento;
	}
	public void setDataFechamento(LocalDateTime dataFechamento) {
		this.dataFechamento = dataFechamento;
	}
	
	@NotBlank(message="A descrição do problema deve ser informado")
	@Column(name="descricao")
	@Lob()
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
		
	///@NotBlank(message="O número de Série ou wk do equipamento deve ser informado.")
	//@Column(name="equipamneto")
	//public String getEquipamento() {
	//	return equipamento;
	//}

	//public void setEquipamento(String equipamento) {
	//	this.equipamento = equipamento;
	//}

	@Lob
	@Column(name="solucao")
	public String getSolucao() {
		return solucao;
	}

	public void setSolucao(String solucao) {
		this.solucao = solucao;
	}

	@NotNull(message="O tipo da manuenção deve ser informado")
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_manutencao")
	public TipoManutencao getTipoManutencao() {
		return tipoManutencao;
	}
	public void setTipoManutencao(TipoManutencao tipoManutencao) {
		this.tipoManutencao = tipoManutencao;
	}

	//@NotNull(message="O login do usuário deve ser informado")
	@ManyToOne
	@JoinColumn(name="codigo_login_entrada")
	public Login getLoginEntrada() {
		return loginEntrada;
	}
	public void setLoginEntrada(Login loginEntrada) {
		this.loginEntrada = loginEntrada;
	}
	
	//@NotNull(message="O login do usuário deve ser informado")
	@ManyToOne
	@JoinColumn(name="codigo_login_saida")
	public Login getLoginSaida() {
		return loginSaida;
	}
	public void setLoginSaida(Login loginSaida) {
		this.loginSaida = loginSaida;
	}
	
	@NotNull(message="O status da manutenção deve ser informado")
	@Enumerated(EnumType.STRING)
	@Column(name="status_manutencao")
	public StatusManutencao getStatusManutencao() {
		return statusManutencao;
	}
	public void setStatusManutencao(StatusManutencao statusManutencao) {
		this.statusManutencao = statusManutencao;
	}
	
	@Lob
	@Column(name="obs")
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
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
		Manutencao other = (Manutencao) obj;
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
		return this.codigo == null;
	}
	
	@Transient
	public String UsuarioAutenticado()
	{
		String usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getName();
		if(usuarioLogado == null)
		{
			return "null";
		}
		return usuarioLogado;
		
	}
	
	@Transient
	public boolean isManutencaoAberto()
	{
		return this.statusManutencao.equals(StatusManutencao.ABERTO);
	}
	
	@Transient
	public boolean isManutencaoSemSolucao()
	{
		return this.statusManutencao.equals(StatusManutencao.SEMSOLUCAO);
	}
	
	@Transient
	public boolean isManutencaoFechada()
	{
		return this.statusManutencao.equals(StatusManutencao.FECHADO);
	}
	
	

}
