package br.com.pirelli.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.pirelli.model.OsComputador;
import br.com.pirelli.model.OsImpressora;
import br.com.pirelli.repository.helper.osimpressora.OsImpressoraQueries;

@Repository
public interface OsImpressoras extends JpaRepository<OsImpressora, Long>, OsImpressoraQueries
{
	@Query(value="select * from manutencao m inner join os_impressora os on m.codigo = os.codigo_impressora where m.tipo_equipamento = 'impressora' and m.status_manutencao = 'ABERTO' order by(m.data_entrada) asc",
			nativeQuery=true)
	public List<OsComputador> pesquisarOsImpressoraAberto();

}
