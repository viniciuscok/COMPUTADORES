/*package br.com.pirelli.repository.helper.manutencao;

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
import org.springframework.util.StringUtils;

import br.com.pirelli.filter.ManutencaoFilter;
import br.com.pirelli.model.Manutencao;
import br.com.pirelli.repository.PaginacaoUtil;

public class ManutencoesImpl implements ManutencoesQueries
{

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public Page<Manutencao> filtro(ManutencaoFilter manutencaoFilter, Pageable pageable) 
	{
		@SuppressWarnings("deprecation")
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Manutencao.class);
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(manutencaoFilter, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(manutencaoFilter));
	}
	
	
	@SuppressWarnings("deprecation")
	private Long total(ManutencaoFilter manutencaoFilter) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Manutencao.class);
		adicionarFiltro(manutencaoFilter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	private void adicionarFiltro(ManutencaoFilter manutencaoFilter, Criteria criteria)
	{
		if (manutencaoFilter != null) 
		{
			if (isCodigoPresente(manutencaoFilter)) 
			{
				criteria.add(Restrictions.eq("codigo", manutencaoFilter.getCodigo()));
			}
			
			if (!StringUtils.isEmpty(manutencaoFilter.getEquipamento())) 
			{
				criteria.add(Restrictions.ilike("equipamento", manutencaoFilter.getEquipamento(), MatchMode.ANYWHERE));
			}
			
			if(isLoginEntradaPresente(manutencaoFilter))
			{
				criteria.add(Restrictions.eq("loginEntrada", manutencaoFilter.getLoginEntrada()));
			}
			
			if(isLoginSaidaPresente(manutencaoFilter))
			{
				criteria.add(Restrictions.eq("loginSaida", manutencaoFilter.getLoginSaida()));
			}
			
			if (manutencaoFilter.getTipoManutencao() != null) {
				criteria.add(Restrictions.eq("tipoManutencao", manutencaoFilter.getTipoManutencao()));
			}
			
			if (manutencaoFilter.getStatusManutencao() != null) {
				criteria.add(Restrictions.eq("statusManutencao", manutencaoFilter.getStatusManutencao()));
			}						
		}
	}	
	
	private boolean isCodigoPresente(ManutencaoFilter manutencaoFilter)
	{
		return manutencaoFilter.getCodigo() != null;
	}
	
	private boolean isLoginEntradaPresente(ManutencaoFilter manutencaoFilter) {
		return manutencaoFilter.getLoginEntrada() != null && manutencaoFilter.getLoginEntrada().getCodigo() != null;
	}
	
	private boolean isLoginSaidaPresente(ManutencaoFilter manutencaoFilter) {
		return manutencaoFilter.getLoginSaida() != null && manutencaoFilter.getLoginSaida().getCodigo() != null;
	}

}
*/