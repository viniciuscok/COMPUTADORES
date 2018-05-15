package br.com.pirelli.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.pirelli.model.Modelo;
import br.com.pirelli.model.TipoModelo;

@Repository
public interface Modelos extends JpaRepository<Modelo, Long>
{
	public Optional<Modelo> findByNomeAndTipoModelo(String nome, TipoModelo tipoModelo);
	
	@Query(value="SELECT * FROM Modelo WHERE nome like ?1% order by nome ASC", 
			countQuery="SELECT count(*) FROM Modelo WHERE nome like ?1% order by nome ASC", 
			nativeQuery=true)
	public Page<Modelo> findByNome(String nome, Pageable pageable);

	@Query(value="SELECT * FROM Modelo m inner join m.TipoModelotipo_modelo as tp on m.codigo_tipo_modelo = tp.codigo where tp.nome = ?1", 
			nativeQuery=true)
	public List<Modelo> filtro(String nome);
	
	@Query(value="select m.* from modelo m inner join tipo_modelo t on m.codigo_tipo_modelo = t.codigo where t.nome = 'toner' order by(m.nome) asc",
			nativeQuery=true)
	public List<Modelo> buscarModeloTonerOrdemCrescente();
	
	@Query(value="select m.* from modelo m inner join tipo_modelo t on m.codigo_tipo_modelo = t.codigo where t.nome = 'impressora' order by(m.nome) asc",
			nativeQuery=true)
	public List<Modelo> buscarModeloImpressoraOrdemCrescente();
	
	@Query(value="select m.* from modelo m inner join tipo_modelo t on m.codigo_tipo_modelo = t.codigo where t.nome = ?1 order by(m.nome) asc",
			nativeQuery=true)
	public List<Modelo> teste(String nome);
}
