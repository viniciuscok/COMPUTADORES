package br.com.pirelli.repository.helper.monitor;

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

import br.com.pirelli.filter.MonitorFilter;
import br.com.pirelli.model.Monitor;
import br.com.pirelli.repository.PaginacaoUtil;

public class MonitoresImpl implements MonitoresQueries 
{
	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Transactional(readOnly = true)
	@Override
	public Page<Monitor> filtro(MonitorFilter monitorFilter, Pageable pageable) {

		Criteria criteria = manager.unwrap(Session.class).createCriteria(Monitor.class);
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(monitorFilter, criteria);

		return new PageImpl<>(criteria.list(), pageable, total(monitorFilter));
	}

	@SuppressWarnings("deprecation")
	private Long total(MonitorFilter monitorFilter) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Monitor.class);
		adicionarFiltro(monitorFilter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(MonitorFilter monitorFilter, Criteria criteria) {
		if (monitorFilter != null) {
			if (!StringUtils.isEmpty(monitorFilter.getNumeroDeSerie())) {
				criteria.add(Restrictions.ilike("numeroDeSerie", monitorFilter.getNumeroDeSerie(), MatchMode.ANYWHERE));
			}

			if (isModeloPresente(monitorFilter)) {
				criteria.add(Restrictions.eq("modelo", monitorFilter.getModelo()));
			}

			if (monitorFilter.getStatus() != null) {
				criteria.add(Restrictions.eq("status", monitorFilter.getStatus()));
			}

			if (isMarcaPresente(monitorFilter)) {
				criteria.add(Restrictions.eq("marca", monitorFilter.getMarca()));
			}

			if (isSetorPresente(monitorFilter)) {
				criteria.add(Restrictions.eq("setor", monitorFilter.getSetor()));
			}
			
		}
	}
	
	private boolean isModeloPresente(MonitorFilter monitorFilter) {
		return monitorFilter.getModelo() != null && monitorFilter.getModelo().getCodigo() != null;
	}

	private boolean isMarcaPresente(MonitorFilter monitorFilter) {
		return monitorFilter.getMarca() != null && monitorFilter.getMarca().getCodigo() != null;
	}

	private boolean isSetorPresente(MonitorFilter monitorFilter) {
		return monitorFilter.getSetor() != null && monitorFilter.getSetor().getCodigo() != null;
	}

}
