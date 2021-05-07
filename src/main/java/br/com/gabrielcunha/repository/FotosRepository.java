package br.com.gabrielcunha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gabrielcunha.entity.Foto;

public interface FotosRepository extends JpaRepository<Foto, Long>{
	
}
