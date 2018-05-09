package br.com.pirelli.repository.helper.toner;

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

import br.com.pirelli.filter.TonerFilter;
import br.com.pirelli.model.Toner;
import br.com.pirelli.repository.PaginacaoUtil;

public class TonersImpl implements TonersQueries
{

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Transactional(readOnly = true)
	@Override
	public Page<Toner> filtro(TonerFilter tonerFilter, Pageable pageable) 
	{
		
			Criteria criteria = manager.unwrap(Session.class).createCriteria(Toner.class);
			paginacaoUtil.preparar(criteria, pageable);
			adicionarFiltro(tonerFilter, criteria);
			
			return new PageImpl<>(criteria.list(), pageable, total(tonerFilter));
		}
		
		
		
		
		@SuppressWarnings("deprecation")
		private Long total(TonerFilter tonerFilter) {
			Criteria criteria = manager.unwrap(Session.class).createCriteria(Toner.class);
			adicionarFiltro(tonerFilter, criteria);
			criteria.setProjection(Projections.rowCount());
			return (Long) criteria.uniqueResult();
		}
		
		private void adicionarFiltro(TonerFilter tonerFilter, Criteria criteria)
		{
			if (tonerFilter != null) 
			{
				
				if(isModeloPresente(tonerFilter))
				{
					criteria.add(Restrictions.eq("modelo", tonerFilter.getModelo()));
				}
				
			}
		}	
		
		private boolean isModeloPresente(TonerFilter tonerFilter) {
			return tonerFilter.getModelo() != null && tonerFilter.getModelo().getCodigo() != null;
		}
		
}
