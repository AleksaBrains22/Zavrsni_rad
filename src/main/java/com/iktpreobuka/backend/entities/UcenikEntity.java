package com.iktpreobuka.backend.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DiscriminatorValue("Ucenik")
public class UcenikEntity extends KorisnikEntity{
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "roditelj_id")
	private RoditeljEntity roditelj;
	@OneToMany(mappedBy = "id", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<ZakljucnaOcenaEntity> zakljucnaOcena = new ArrayList<>();
	@OneToMany(mappedBy = "id", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JsonIgnore
	private List<OcenaEntity> ocena = new ArrayList<>();
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "odeljenje_id")
	private OdeljenjeEntity odeljenjeEntity;
	@JsonIgnore
	public RoditeljEntity getRoditelj() {
		return roditelj;
	}
	@JsonIgnore
	public void setRoditelj(RoditeljEntity roditelj) {
		this.roditelj = roditelj;
	}
	public List<ZakljucnaOcenaEntity> getZakljucnaOcena() {
		return zakljucnaOcena;
	}
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
	public OdeljenjeEntity getOdeljenjeEntity() {
		return odeljenjeEntity;
	}
	public void setOdeljenjeEntity(OdeljenjeEntity odeljenjeEntity) {
		this.odeljenjeEntity = odeljenjeEntity;
	}
	
	
	
}