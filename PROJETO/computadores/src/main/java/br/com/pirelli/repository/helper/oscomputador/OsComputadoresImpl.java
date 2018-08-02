package br.com.pirelli.repository.helper.oscomputador;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import br.com.pirelli.filter.OsComputadorFilter;
import br.com.pirelli.model.OsComputador;
import br.com.pirelli.repository.PaginacaoUtil;

public class OsComputadoresImpl implements OsComputadoresQueries
{
	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public Page<OsComputador> filtro(OsComputadorFilter osComputadorFilter, Pageable pageable) 
	{
		@SuppressWarnings("deprecation")
		Criteria criteria = manager.unwrap(Session.class).createCriteria(OsComputador.class);
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(osComputadorFilter, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(osComputadorFilter));
	}
	
	
	@SuppressWarnings("deprecation")
	private Long total(OsComputadorFilter osComputadorFilter) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(OsComputador.class);
		adicionarFiltro(osComputadorFilter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	private void adicionarFiltro(OsComputadorFilter osComputadorFilter, Criteria criteria)
	{
		if (osComputadorFilter != null) 
		{
			if (isCodigoPresente(osComputadorFilter)) 
			{
				criteria.add(Restrictions.eq("codigo", osComputadorFilter.getCodigo()));
			}
			
			if(isComputadorPresente(osComputadorFilter))
			{
				criteria.add(Restrictions.eq("computador", osComputadorFilter.getComputador()));
			}
			
			if(isLoginEntradaPresente(osComputadorFilter))
			{
				criteria.add(Restrictions.eq("loginEntrada", osComputadorFilter.getLoginEntrada()));
			}
			
			if(isLoginSaidaPresente(osComputadorFilter))
			{
				criteria.add(Restrictions.eq("loginSaida", osComputadorFilter.getLoginSaida()));
			}
			
			if (osComputadorFilter.getTipoManutencao() != null) {
				criteria.add(Restrictions.eq("tipoManutencao", osComputadorFilter.getTipoManutencao()));
			}
			
			if (osComputadorFilter.getStatusManutencao() != null) {
				criteria.add(Restrictions.eq("statusManutencao", osComputadorFilter.getStatusManutencao()));
			}						
		}
	}	
	
	private boolean isCodigoPresente(OsComputadorFilter osComputadorFilter)
	{
		return osComputadorFilter.getCodigo() != null;
	}
	
	private boolean isComputadorPresente(OsComputadorFilter osComputadorFilter) {
		return osComputadorFilter.getComputador() != null && osComputadorFilter.getComputador().getCodigo() != null;
	}
	
	private boolean isLoginEntradaPresente(OsComputadorFilter osComputadorFilter) {
		return osComputadorFilter.getLoginEntrada() != null && osComputadorFilter.getLoginEntrada().getCodigo() != null;
	}
	
	private boolean isLoginSaidaPresente(OsComputadorFilter osComputadorFilter) {
		return osComputadorFilter.getLoginSaida() != null && osComputadorFilter.getLoginSaida().getCodigo() != null;
	}

}
