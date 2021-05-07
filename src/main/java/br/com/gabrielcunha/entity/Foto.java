package br.com.gabrielcunha.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Table(name = "fotos")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Foto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fot_id")
	@EqualsAndHashCode.Include
	private Long id;

	@Column(name = "fot_caminho")
	private String caminho;

	@Column(name = "fot_descricao")
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "min_id")
	private Miniatura miniatura;
	
	
	

}
