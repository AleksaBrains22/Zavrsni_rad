package com.iktpreobuka.backend.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OdeljenjeEntity {
	@Id
	@GeneratedValue
	private Long id;
	@NotNull(message = "ne moze biti prazno polje")
	@Column(name = "ime_odeljenja")
	private String name;
	@NotNull(message = "ne moze biti prazno polje")
	@Column(name = "razred_odeljenja")
	private Razred razred;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "Nastavnik_id")
	private NastavnikEntity nastavnik;
	
	@JsonIgnore
	@OneToMany(mappedBy = "id", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<UcenikEntity> ucenik = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Razred getRazred() {
		return razred;
	}

	public void setRazred(Razred razred) {
		this.razred = razred;
	}

	public NastavnikEntity getNastavnik() {
		return nastavnik;
	}

	public void setNastavnik(NastavnikEntity nastavnik) {
		this.nastavnik = nastavnik;
	}

	public OdeljenjeEntity(Long id, String name, Razred razred, NastavnikEntity nastavnik) {
		super();
		this.id = id;
		this.name = name;
		this.razred = razred;
		this.nastavnik = nastavnik;
	}

	public OdeljenjeEntity() {
		super();
	}

}
