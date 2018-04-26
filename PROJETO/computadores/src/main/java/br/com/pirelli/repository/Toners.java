package br.com.pirelli.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pirelli.model.Marca;
import br.com.pirelli.model.Modelo;
import br.com.pirelli.model.Toner;

@Repository
public interface Toners extends JpaRepository<Toner, Long> 
{
	Optional<Toner> findByModeloAndMarca(Modelo modelo, Marca marca);
	
	
}
