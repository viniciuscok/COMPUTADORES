package br.com.pirelli.repository.helper.osimpressora;

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

import br.com.pirelli.filter.OsImpressoraFilter;
import br.com.pirelli.model.OsImpressora;
import br.com.pirelli.repository.PaginacaoUtil;

public class OsImpressorasImpl implements OsImpressoraQueries
{
	
	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public Page<OsImpressora> filtro(OsImpressoraFilter osImpressoraFilter, Pageable pageable) 
	{
		@SuppressWarnings("deprecation")
		Criteria criteria = manager.unwrap(Session.class).createCriteria(OsImpressora.class);
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(osImpressoraFilter, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(osImpressoraFilter));
	}
	
	
	@SuppressWarnings("deprecation")
	private Long total(OsImpressoraFilter osImpressoraFilter) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(OsImpressora.class);
		adicionarFiltro(osImpressoraFilter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	private void adicionarFiltro(OsImpressoraFilter osImpressoraFilter, Criteria criteria)
	{
		if (osImpressoraFilter != null) 
		{
			if (isCodigoPresente(osImpressoraFilter)) 
			{
				criteria.add(Restrictions.eq("codigo", osImpressoraFilter.getCodigo()));
			}
			
			if(isImpressoraPresente(osImpressoraFilter))
			{
				criteria.add(Restrictions.eq("impressora", osImpressoraFilter.getImpressora()));
			}
			
			if(isLoginEntradaPresente(osImpressoraFilter))
			{
				criteria.add(Restrictions.eq("loginEntrada", osImpressoraFilter.getLoginEntrada()));
			}
			
			if(isLoginSaidaPresente(osImpressoraFilter))
			{
				criteria.add(Restrictions.eq("loginSaida", osImpressoraFilter.getLoginSaida()));
			}
			
			if (osImpressoraFilter.getTipoManutencao() != null) {
				criteria.add(Restrictions.eq("tipoManutencao", osImpressoraFilter.getTipoManutencao()));
			}
			
			if (osImpressoraFilter.getStatusManutencao() != null) {
				criteria.add(Restrictions.eq("statusManutencao", osImpressoraFilter.getStatusManutencao()));
			}						
		}
	}	
	
	private boolean isCodigoPresente(OsImpressoraFilter osImpressoraFilter)
	{
		return osImpressoraFilter.getCodigo() != null;
	}
	
	private boolean isImpressoraPresente(OsImpressoraFilter osImpressoraFilter) {
		return osImpressoraFilter.getImpressora() != null && osImpressoraFilter.getImpressora().getCodigo() != null;
	}
	
	private boolean isLoginEntradaPresente(OsImpressoraFilter osImpressoraFilter) {
		return osImpressoraFilter.getLoginEntrada() != null && osImpressoraFilter.getLoginEntrada().getCodigo() != null;
	}
	
	private boolean isLoginSaidaPresente(OsImpressoraFilter osImpressoraFilter) {
		return osImpressoraFilter.getLoginSaida() != null && osImpressoraFilter.getLoginSaida().getCodigo() != null;
	}

}
