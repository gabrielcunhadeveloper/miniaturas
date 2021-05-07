package br.com.gabrielcunha.entity;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "miniaturas")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Miniatura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "min_id")
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(name = "min_modelo")	
	private String modelo;
	
	@Column(name = "min_ano")	
	private Year ano;
	
	@Column(name = "min_observacoes")	
	private String observacoes;
	
	@Column(name = "min_edicao")	
	private String edicao;
	
	@Column(name = "min_escala")	
	private String escala;
	
	@Column(name = "min_valor")	
	private BigDecimal valor;

	@OneToMany(mappedBy = "miniatura")
	private List<Foto> fotos;	

	@ManyToOne
	@JoinColumn(name = "min_fab_id")	
	private Fabricante fabricante;
	
	@ManyToOne
	@JoinColumn(name = "min_tmi_id")		
	private TipoMiniatura tipoMinatura;
	
	@ManyToOne
	@JoinColumn(name = "min_tem_id")		
	private Tema tema;
	
	
}
