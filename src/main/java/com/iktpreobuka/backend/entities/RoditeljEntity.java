package com.iktpreobuka.backend.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@DiscriminatorValue("Roditelj")
public class RoditeljEntity extends KorisnikEntity{
	@JsonIgnore
	@OneToMany(mappedBy = "id", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<UcenikEntity> ucenik = new ArrayList<>();
	@JsonIgnore
	public List<UcenikEntity> getUcenik() {
		return ucenik;
	}
	@JsonIgnore
	public void setUcenik(List<UcenikEntity> ucenik) {
		this.ucenik = ucenik;
	}
	
	
}
