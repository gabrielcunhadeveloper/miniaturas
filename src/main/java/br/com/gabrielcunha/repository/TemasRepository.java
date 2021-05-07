package br.com.gabrielcunha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gabrielcunha.entity.Tema;

public interface TemasRepository extends JpaRepository<Tema, Long>{

}
