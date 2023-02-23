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

import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
public class PredmetEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Predmet_id")
	private Long id;
	@Column(name = "Ime_predmeta")
	private String name;
	@Column(name = "fond_casova")
	private Integer FondCasova;
	@Column(name = "razred")
	private Razred razred;
	@JsonManagedReference
	@OneToMany(mappedBy = "id", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<ZakljucnaOcenaEntity> zakljucnaOcena = new ArrayList<>();
	@JsonManagedReference
	@OneToMany(mappedBy = "id", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<OcenaEntity> ocena = new ArrayList<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinTable(name = "Nastavnik_id", joinColumns = {
			@JoinColumn(name = "Predmet_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "Nastavnik_id", nullable = false, updatable = false) })
	protected Set<NastavnikEntity> categories = new HashSet<NastavnikEntity>();
	
	
}
