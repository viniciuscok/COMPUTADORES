package br.com.pirelli.repository.helper.computador;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import br.com.pirelli.filter.ComputadorFilter;
import br.com.pirelli.model.Computador;
import br.com.pirelli.repository.PaginacaoUtil;

public class ComputadoresImpl implements ComputadoresQueries
{

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Transactional(readOnly = true)
	@Override
	public Page<Computador> filtro(ComputadorFilter computadorFilter, Pageable pageable) 
	{
		
			Criteria criteria = manager.unwrap(Session.class).createCriteria(Computador.class);
			paginacaoUtil.preparar(criteria, pageable);
			adicionarFiltro(computadorFilter, criteria);
			
			return new PageImpl<>(criteria.list(), pageable, total(computadorFilter));
		}
		
		
		
		
		@SuppressWarnings("deprecation")
		private Long total(ComputadorFilter computadorFilter) {
			Criteria criteria = manager.unwrap(Session.class).createCriteria(Computador.class);
			adicionarFiltro(computadorFilter, criteria);
			criteria.setProjection(Projections.rowCount());
			return (Long) criteria.uniqueResult();
		}
		
		private void adicionarFiltro(ComputadorFilter computadorFilter, Criteria criteria)
		{
			if (computadorFilter != null) 
			{
				if (!StringUtils.isEmpty(computadorFilter.getNome())) 
				{
					criteria.add(Restrictions.ilike("nome", computadorFilter.getNome(), MatchMode.ANYWHERE));
				}
			}
		}	

}
