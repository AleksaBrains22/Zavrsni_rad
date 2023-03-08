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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DiscriminatorValue("Ucenik")
public class UcenikEntity extends KorisnikEntity{
	
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "roditelj_id")
	private RoditeljEntity roditelj;
	
	@JsonIgnore
	@OneToMany(mappedBy = "id", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<ZakljucnaOcenaEntity> zakljucnaOcena = new ArrayList<>();
	@JsonIgnore
	@OneToMany(mappedBy = "id", fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	private List<OcenaEntity> ocena = new ArrayList<>();
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "odeljenje_id")
	private OdeljenjeEntity odeljenjeEntity;
	
	public RoditeljEntity getRoditelj() {
		return roditelj;
	}
	
	public void setRoditelj(RoditeljEntity roditelj) {
		this.roditelj = roditelj;
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
	public OdeljenjeEntity getOdeljenjeEntity() {
		return odeljenjeEntity;
	}
	public void setOdeljenjeEntity(OdeljenjeEntity odeljenjeEntity) {
		this.odeljenjeEntity = odeljenjeEntity;
	}

	public UcenikEntity(Long id,
			@NotNull(message = "Ime korisnika mora biti popunjeno") @Size(min = 3, max = 30, message = "Ime mora biti izmedju {min} i {max} broja slova.") String ime,
			@NotNull(message = "Prezime korisnika mora biti popunjeno") @Size(min = 3, max = 30, message = "Prezime mora biti izmedju {min} i {max} broja slova.") String prezime,
			@NotNull(message = "Password ne moze biti prazan") String password,
			@NotNull(message = "Username ne moze biti prazan") @Size(min = 3, max = 30, message = "Username mora biti izmedju {min} i {max} broja slova.") String username,
			Integer version, @Email String email, UlogaEntity uloga, RoditeljEntity roditelj,
			List<ZakljucnaOcenaEntity> zakljucnaOcena, List<OcenaEntity> ocena, OdeljenjeEntity odeljenjeEntity) {
		super(id, ime, prezime, password, username, version, email, uloga);
		this.roditelj = roditelj;
		this.zakljucnaOcena = zakljucnaOcena;
		this.ocena = ocena;
		this.odeljenjeEntity = odeljenjeEntity;
	}

	public UcenikEntity(Long id,
			@NotNull(message = "Ime korisnika mora biti popunjeno") @Size(min = 3, max = 30, message = "Ime mora biti izmedju {min} i {max} broja slova.") String ime,
			@NotNull(message = "Prezime korisnika mora biti popunjeno") @Size(min = 3, max = 30, message = "Prezime mora biti izmedju {min} i {max} broja slova.") String prezime,
			@NotNull(message = "Password ne moze biti prazan") String password,
			@NotNull(message = "Username ne moze biti prazan") @Size(min = 3, max = 30, message = "Username mora biti izmedju {min} i {max} broja slova.") String username,
			Integer version, @Email String email, UlogaEntity uloga) {
		super(id, ime, prezime, password, username, version, email, uloga);
	}

	public UcenikEntity() {
	}
	
	
	
}