package com.iktpreobuka.backend.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@DiscriminatorValue(value = "Nastavnik")
public class NastavnikEntity extends KorisnikEntity {

	@JsonManagedReference
	@OneToMany(mappedBy = "id", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<OdeljenjeEntity> odeljenjeEntities = new ArrayList<>();

	@JsonManagedReference
	@OneToMany(mappedBy = "id", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<OcenaEntity> ocena = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinTable(name = "Predmet_id", joinColumns = {
			@JoinColumn(name = "Nastavnik_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "Predmet_id", nullable = false, updatable = false) })
	protected Set<PredmetEntity> categories = new HashSet<PredmetEntity>();

}
