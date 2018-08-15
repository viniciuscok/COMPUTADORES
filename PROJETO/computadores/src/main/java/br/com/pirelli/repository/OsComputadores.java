package br.com.pirelli.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.pirelli.model.OsComputador;
import br.com.pirelli.repository.helper.oscomputador.OsComputadoresQueries;

@Repository
public interface OsComputadores extends JpaRepository<OsComputador, Long>, OsComputadoresQueries
{
	
	@Query(value="select * from manutencao m inner join os_computador os on m.codigo = os.codigo_computador where m.tipo_equipamento = 'computador' and m.status_manutencao = 'ABERTO' order by(m.data_entrada) asc",
				nativeQuery=true)
	public List<OsComputador> pesquisarOsComputadorAberto();

}
