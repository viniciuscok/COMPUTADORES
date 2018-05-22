package br.com.pirelli.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.pirelli.model.Login;

@Repository
public interface Logins extends JpaRepository<Login, Long>
{
	Optional<Login> findByEmailStartingWithIgnoreCase(String email);
	
	@Query(value="select * from Login where email = ?1 and ativo = 1",
			nativeQuery=true)
	public Optional<Login> verificarLogin(String email);
	
	@Query(value="select distinct p.nome from Login u inner join u.grupos g inner join g.permissoes p where u = ?1",
			nativeQuery=false)
	public List<String> permissoes(Login login);
	
	@Query(value = "SELECT * FROM Login WHERE nome like ?1% order by nome ASC", 
			countQuery = "SELECT count(*) FROM Login WHERE nome like ?1% order by nome ASC",
		    nativeQuery = true)
	public Page<Login> findByNome(String nome, Pageable pageable);
}
