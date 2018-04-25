package br.com.pirelli.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pirelli.model.Toner;

@Repository
public interface Toners extends JpaRepository<Toner, Long> 
{

}
