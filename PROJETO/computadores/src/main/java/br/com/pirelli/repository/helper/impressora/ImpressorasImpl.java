package br.com.pirelli.repository.helper.impressora;

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
				if (!StringUtils.isEmpty(impressoraFilter.getNumeroDeSerie())) 
				{
					criteria.add(Restrictions.ilike("numeroDeSerie", impressoraFilter.getNumeroDeSerie(), MatchMode.ANYWHERE));
				}
				
				if(isMarcaPresente(impressoraFilter))
				{
					criteria.add(Restrictions.eq("marca", impressoraFilter.getMarca()));
				}
				
				if(isModeloPresente(impressoraFilter))
				{
					criteria.add(Restrictions.eq("modelo", impressoraFilter.getModelo()));
				}
				
				if(isSetorPresente(impressoraFilter))
				{
					criteria.add(Restrictions.eq("setor", impressoraFilter.getSetor()));
				}
				
				if (impressoraFilter.getCategoriaImpressora() != null) {
					criteria.add(Restrictions.eq("categoriaImpressora", impressoraFilter.getCategoriaImpressora()));
				}
				
				if (impressoraFilter.getTipoImpressora() != null) {
					criteria.add(Restrictions.eq("tipoImpressora", impressoraFilter.getTipoImpressora()));
				}
				
				if(impressoraFilter.getStatus() != null)
				{
					criteria.add(Restrictions.eq("status", impressoraFilter.getStatus()));
				}
				
				
				
				
			}
		}	
		
		private boolean isModeloPresente(ImpressoraFilter impressoraFilter) {
			return impressoraFilter.getModelo() != null && impressoraFilter.getModelo().getCodigo() != null;
		}
		
		private boolean isMarcaPresente(ImpressoraFilter impressoraFilter) {
			return impressoraFilter.getMarca() != null && impressoraFilter.getMarca().getCodigo() != null;
		}
		
		private boolean isSetorPresente(ImpressoraFilter impressoraFilter) {
			return impressoraFilter.getSetor() != null && impressoraFilter.getSetor().getCodigo() != null;
		}

}
