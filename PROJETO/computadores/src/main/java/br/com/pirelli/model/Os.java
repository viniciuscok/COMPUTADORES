package br.com.pirelli.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="os")
public class Os implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	private Long codigo;
	private String descricao;
	private LocalDate data;
	private Login login;
	private Equipamento equipamento;
	private TipoEquipamento tipoEquipamento;
	private String obs;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo", nullable=false, unique=true)
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	public Equipamento getEquipamento() {
		return equipamento;
	}
	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}
	
	
	
	public String getObs() {
		return obs;
	}
	public TipoEquipamento getTipoEquipamento() {
		return tipoEquipamento;
	}
	public void setTipoEquipamento(TipoEquipamento tipoEquipamento) {
		this.tipoEquipamento = tipoEquipamento;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	
	
	
	@Transient
	public boolean isNova()
	{
		return this.codigo == null;
	}
	
	

}
