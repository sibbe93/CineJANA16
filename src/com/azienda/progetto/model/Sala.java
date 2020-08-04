package com.azienda.progetto.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Sala {
	
	@Column(unique = true ,nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private Integer numeroPosti;
	
	@OneToMany(mappedBy = "sala")
	private List<Spettacolo> spettacoli;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	public Sala() {
		this(null,null);
	}
	
	public Sala(String nome, Integer numeroPosti) {
		super();
		this.nome = nome;
		this.numeroPosti = numeroPosti;
		spettacoli = new ArrayList<Spettacolo>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getNumeroPosti() {
		return numeroPosti;
	}

	public void setNumeroPosti(Integer numeroPosti) {
		this.numeroPosti = numeroPosti;
	}

	public List<Spettacolo> getSpettacoli() {
		return spettacoli;
	}

	public void setSpettacoli(List<Spettacolo> spettacoli) {
		this.spettacoli = spettacoli;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	
}
