package br.com.pirelli.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pirelli.model.Marca;
import br.com.pirelli.model.Modelo;
import br.com.pirelli.model.Toner;
import br.com.pirelli.repository.helper.toner.TonersQueries;

@Repository
public interface Toners extends JpaRepository<Toner, Long>, TonersQueries
{
	Optional<Toner> findByModeloAndMarca(Modelo modelo, Marca marca);
	
}
