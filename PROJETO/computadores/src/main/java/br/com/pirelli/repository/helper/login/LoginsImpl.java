package br.com.pirelli.repository.helper.login;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.pirelli.filter.LoginFilter;
import br.com.pirelli.model.Login;

public class LoginsImpl implements LoginsQueries
{
	
	@PersistenceContext
	private EntityManager manager;

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Transactional(readOnly = true)
	@Override
	public List<Login> filtrar(LoginFilter loginFilter) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Login.class);
		
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		adicionarFiltro(loginFilter, criteria);
		
		return criteria.list();
	}

	private void adicionarFiltro(LoginFilter loginFilter, Criteria criteria) {
		if (loginFilter != null) {
			if (!StringUtils.isEmpty(loginFilter.getNome())) {
				criteria.add(Restrictions.ilike("nome", loginFilter.getNome(), MatchMode.ANYWHERE));
			}
			
			if (!StringUtils.isEmpty(loginFilter.getEmail())) {
				criteria.add(Restrictions.ilike("email", loginFilter.getEmail(), MatchMode.START));
			}
		}
	}

}
