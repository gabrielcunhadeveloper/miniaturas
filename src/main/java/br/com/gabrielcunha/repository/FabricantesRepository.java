package br.com.gabrielcunha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.gabrielcunha.entity.Fabricante;

public interface FabricantesRepository extends JpaRepository<Fabricante, Long>{
	
	@Query("select f from Fabricante f where f.nome = :nome")
	Fabricante findByNome(@Param("nome") String nome);
	
}