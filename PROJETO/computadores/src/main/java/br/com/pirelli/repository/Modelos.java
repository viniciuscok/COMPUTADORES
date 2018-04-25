package br.com.pirelli.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pirelli.model.Modelo;
import br.com.pirelli.model.TipoModelo;

@Repository
public interface Modelos extends JpaRepository<Modelo, Long>
{
	public Optional<Modelo> findByNomeAndTipoModelo(String nome, TipoModelo tipoModelo);

}
