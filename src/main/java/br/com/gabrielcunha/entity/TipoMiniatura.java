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
@Table(name = "tipo_miniaturas")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TipoMiniatura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tmi_id")
	@EqualsAndHashCode.Include
	private Long id;

	@Column(name = "tmi_tipo")
	private String tipo;
	
}
