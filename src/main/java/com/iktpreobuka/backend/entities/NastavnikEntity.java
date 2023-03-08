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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@DiscriminatorValue(value = "Nastavnik")
public class NastavnikEntity extends KorisnikEntity {
	@JsonIgnore
	@OneToMany(mappedBy = "id", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<OdeljenjeEntity> odeljenjeEntities = new ArrayList<>();
	@JsonIgnore
	@OneToMany(mappedBy = "id", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<OcenaEntity> ocena = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinTable(name = "Nastavnik_koj_predaje_predmet", joinColumns = {
			@JoinColumn(name = "Nastavnik_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "Predmet_id", nullable = false, updatable = false) })
	protected Set<PredmetEntity> predmetKojNastavnikpredaje = new HashSet<PredmetEntity>();
	@JsonIgnore
	public List<OdeljenjeEntity> getOdeljenjeEntities() {
		return odeljenjeEntities;
	}
	@JsonIgnore
	public void setOdeljenjeEntities(List<OdeljenjeEntity> odeljenjeEntities) {
		this.odeljenjeEntities = odeljenjeEntities;
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
	public Set<PredmetEntity> getPredmetKojNastavnikpredaje() {
		return this.predmetKojNastavnikpredaje;
	}

	public void setPredmetKojNastavnikpredaje(Set<PredmetEntity> predmetKojNastavnikpredaje) {
		this.predmetKojNastavnikpredaje = predmetKojNastavnikpredaje;
	}
	

}
