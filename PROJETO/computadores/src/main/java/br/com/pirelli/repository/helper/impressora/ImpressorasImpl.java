package br.com.pirelli.repository.helper.impressora;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import br.com.pirelli.filter.ImpressoraFilter;
import br.com.pirelli.model.Impressora;
import br.com.pirelli.repository.PaginacaoUtil;

public class ImpressorasImpl implements ImpressorasQueries
{

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Transactional(readOnly = true)
	@Override
	public Page<Impressora> filtro(ImpressoraFilter impressoraFilter, Pageable pageable) 
	{
		
			Criteria criteria = manager.unwrap(Session.class).createCriteria(Impressora.class);
			paginacaoUtil.preparar(criteria, pageable);
			adicionarFiltro(impressoraFilter, criteria);
			
			return new PageImpl<>(criteria.list(), pageable, total(impressoraFilter));
		}
		
		
		
		
		@SuppressWarnings("deprecation")
		private Long total(ImpressoraFilter impressoraFilter) {
			Criteria criteria = manager.unwrap(Session.class).createCriteria(Impressora.class);
			adicionarFiltro(impressoraFilter, criteria);
			criteria.setProjection(Projections.rowCount());
			return (Long) criteria.uniqueResult();
		}
		
		private void adicionarFiltro(ImpressoraFilter impressoraFilter, Criteria criteria)
		{
			if (impressoraFilter != null) 
			{
				
			}
		}	

}
