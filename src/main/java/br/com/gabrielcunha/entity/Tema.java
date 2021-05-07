package br.com.gabrielcunha.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "temas")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Tema {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tem_id")
	@EqualsAndHashCode.Include
	private Long id;

	@Column(name = "tem_nome")
	private String nome;

	
	
}
