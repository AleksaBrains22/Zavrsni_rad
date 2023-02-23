package com.iktpreobuka.backend.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;




@Entity
public class PredmetEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Predmet_id")
	private Long id;
	@Column(name = "Ime_predmeta")
	@NotNull(message ="ne moze biti prazno polje")
	private String name;
	@Column(name = "fond_casova")
	private Integer FondCasova;
	@NotNull(message ="ne moze biti prazno polje")
	@Column(name = "razred")
	private Razred razred;
	@javax.persistence.Version
	private Integer version;
	@JsonManagedReference
	@JsonIgnore
	@OneToMany(mappedBy = "id", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<ZakljucnaOcenaEntity> zakljucnaOcena = new ArrayList<>();
	@JsonManagedReference
	@JsonIgnore
	@OneToMany(mappedBy = "id", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<OcenaEntity> ocena = new ArrayList<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinTable(name = "Nastavnik_id", joinColumns = {
			@JoinColumn(name = "Predmet_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "Nastavnik_id", nullable = false, updatable = false) })
	protected Set<NastavnikEntity> nastavik = new HashSet<NastavnikEntity>();

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

	public Integer getFondCasova() {
		return FondCasova;
	}

	public void setFondCasova(Integer fondCasova) {
		FondCasova = fondCasova;
	}

	public Razred getRazred() {
		return razred;
	}

	public void setRazred(Razred razred) {
		this.razred = razred;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	@JsonIgnore
	public List<ZakljucnaOcenaEntity> getZakljucnaOcena() {
		return zakljucnaOcena;
	}
	@JsonIgnore
	public void setZakljucnaOcena(List<ZakljucnaOcenaEntity> zakljucnaOcena) {
		this.zakljucnaOcena = zakljucnaOcena;
	}
	@JsonIgnore
	public List<OcenaEntity> getOcena() {
		return ocena;
	}
	@JsonIgnore
	public void setOcena(List<OcenaEntity> ocena) {
		this.ocena = ocena;
	}
	@JsonIgnore
	public Set<NastavnikEntity> getNastavnik() {
		return nastavik;
	}
	@JsonIgnore
	public void setNastavnik(Set<NastavnikEntity> nastavik) {
		this.nastavik = nastavik;
	}

	public PredmetEntity(Long id, @NotNull(message = "ne moze biti prazno polje") String name,
			@NotNull(message = "ne moze biti prazno polje") Integer fondCasova,
			@NotNull(message = "ne moze biti prazno polje") Razred razred, Integer version,
			List<ZakljucnaOcenaEntity> zakljucnaOcena, List<OcenaEntity> ocena, Set<NastavnikEntity> nastavik) {
		super();
		this.id = id;
		this.name = name;
		FondCasova = fondCasova;
		this.razred = razred;
		this.version = version;
		this.zakljucnaOcena = zakljucnaOcena;
		this.ocena = ocena;
		this.nastavik = nastavik;
	}

	public PredmetEntity() {
		super();
	}
	
	
}
