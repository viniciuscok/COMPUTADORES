package br.com.pirelli.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.pirelli.model.Impressora;
import br.com.pirelli.repository.helper.impressora.ImpressorasQueries;

@Repository
public interface Impressoras extends JpaRepository<Impressora, Long>, ImpressorasQueries
{
	public Optional<Impressora> findByEnderecoIPStartingWithIgnoreCase(String enderecoIP);
	
	@Query(value="select * from equipamento e \r\n" + 
			"	inner join impressora i \r\n" + 
			"		on e.codigo = i.codigo \r\n" + 
			"			where e.tipo_equipamento = 'impressora' and i.categoria_impressora = 'impressora_termica' order by(i.codigo) asc",
			nativeQuery=true)
	public List<Impressora> impressoraTermicaOrdemCrescente();
	
	@Query(value="select count(*) from impressora where categoria_impressora = 'IMPRESSORA_TERMICA'",
			nativeQuery=true)
	public Long impressoraTermica();
	
	@Query(value="select count(*) from impressora where categoria_impressora = 'IMPRESSORA_ESCRITORIO'",
			nativeQuery=true)
	public Long impressoraEscritorio();

}
