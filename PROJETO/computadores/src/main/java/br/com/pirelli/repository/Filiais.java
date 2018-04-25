package br.com.pirelli.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pirelli.model.Filial;

@Repository
public interface Filiais extends JpaRepository<Filial, Long>
{
	public Optional<Filial> findByNomeStartingWithIgnoreCase(String nome);

}
