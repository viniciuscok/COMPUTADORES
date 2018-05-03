package br.com.pirelli.filter;

import br.com.pirelli.model.CategoriaImpressora;
import br.com.pirelli.model.Equipamento;
import br.com.pirelli.model.Marca;
import br.com.pirelli.model.Modelo;
import br.com.pirelli.model.Setor;
import br.com.pirelli.model.Status;
import br.com.pirelli.model.TipoImpressora;

public class ImpressoraFilter 
{
	private Long codigo;
	private Equipamento equipamento;
	private Status status;
	private Modelo modelo;
	private Marca marca;
	private Setor setor;
	private TipoImpressora tipoImpressora;
	private CategoriaImpressora categoriaImpressora;
	
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	
	public Equipamento getEquipamento() {
		return equipamento;
	}
	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Modelo getModelo() {
		return modelo;
	}
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
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
	public TipoImpressora getTipoImpressora() {
		return tipoImpressora;
	}
	public void setTipoImpressora(TipoImpressora tipoImpressora) {
		this.tipoImpressora = tipoImpressora;
	}
	public CategoriaImpressora getCategoriaImpressora() {
		return categoriaImpressora;
	}
	public void setCategoriaImpressora(CategoriaImpressora categoriaImpressora) {
		this.categoriaImpressora = categoriaImpressora;
	}
	
	
	

}
