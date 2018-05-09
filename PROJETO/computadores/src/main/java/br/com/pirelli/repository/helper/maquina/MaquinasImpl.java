package br.com.pirelli.repository.helper.maquina;

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
import br.com.pirelli.filter.ImpressoraFilter;
import br.com.pirelli.filter.MaquinaFilter;
import br.com.pirelli.model.Impressora;
import br.com.pirelli.model.Maquina;
import br.com.pirelli.repository.PaginacaoUtil;

public class MaquinasImpl implements MaquinasQueries
{

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Transactional(readOnly = true)
	@Override
	public Page<Maquina> filtro(MaquinaFilter maquinaFilter, Pageable pageable) 
	{
		
			Criteria criteria = manager.unwrap(Session.class).createCriteria(Maquina.class);
			paginacaoUtil.preparar(criteria, pageable);
			adicionarFiltro(maquinaFilter, criteria);
			
			return new PageImpl<>(criteria.list(), pageable, total(maquinaFilter));
		}
		
		
		
		
		@SuppressWarnings("deprecation")
		private Long total(MaquinaFilter maquinaFilter) {
			Criteria criteria = manager.unwrap(Session.class).createCriteria(Maquina.class);
			adicionarFiltro(maquinaFilter, criteria);
			criteria.setProjection(Projections.rowCount());
			return (Long) criteria.uniqueResult();
		}
		
		private void adicionarFiltro(MaquinaFilter maquinaFilter, Criteria criteria)
		{
			if (maquinaFilter != null) 
			{
				if (!StringUtils.isEmpty(maquinaFilter.getNome())) 
				{
					criteria.add(Restrictions.ilike("nome", maquinaFilter.getNome(), MatchMode.ANYWHERE));
				}
				
				if (!StringUtils.isEmpty(maquinaFilter.getSigma())) 
				{
					criteria.add(Restrictions.ilike("sigma", maquinaFilter.getSigma(), MatchMode.ANYWHERE));
				}
				
				if(isComputadorPresente(maquinaFilter))
				{
					criteria.add(Restrictions.eq("computador", maquinaFilter.getComputador()));
				}
				
				if(isSetorPresente(maquinaFilter))
				{
					criteria.add(Restrictions.eq("setor", maquinaFilter.getSetor()));
				}
				
			}
		}	
		
		private boolean isComputadorPresente(MaquinaFilter maquinaFilter)
		{
			return maquinaFilter.getComputador() != null && maquinaFilter.getComputador().getCodigo() != null;
		}
		
		private boolean isSetorPresente(MaquinaFilter maquinaFilter) {
			return maquinaFilter.getSetor() != null && maquinaFilter.getSetor().getCodigo() != null;
		}

}
