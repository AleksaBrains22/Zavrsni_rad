package com.iktpreobuka.backend.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iktpreobuka.backend.entities.OdeljenjeEntity;
import com.iktpreobuka.backend.entities.RoditeljEntity;
import com.iktpreobuka.backend.entities.Uloga;

public class KorisnikDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	@Column(name= "korisnicki_id")
	private Long id;
	@NotNull(message= "Ime korisnika mora biti popunjeno")
	@Size(min = 3, max = 30, message = "Ime mora biti izmedju {min} i {max} broja slova.")
	@Column(name = "Ime_korisnika")
	private String ime;
	@Column
	@NotNull(message= "Prezime korisnika mora biti popunjeno")
	@Size(min = 3, max = 30, message ="Prezime mora biti izmedju {min} i {max} broja slova.")
	private String prezime;
	@Column
	@NotNull(message= "Password ne moze biti prazan")
	private String password;
	@Column
	@NotNull(message="Potvrdite vasu sifru")
	private String confirmedPassword;
	@Column(name = "username", nullable = false ,unique=true)
	@NotNull(message= "Username ne moze biti prazan")
	@Size(min = 3, max = 30, message ="Username mora biti izmedju {min} i {max} broja slova.")
	private String username;
	@Column
	@Version
	private Integer version;
	@Email	
	@Column(name = "email")
	private String email;
	@NotNull(message= "Uloga ne moze biti prazna")
	private Uloga uloga;
	
	private OdeljenjeEntity odeljenje;
	
	private RoditeljEntity roditelj;
	
	
	
	public KorisnikDTO(Long id,
			@NotNull(message = "Ime korisnika mora biti popunjeno") @Size(min = 3, max = 30, message = "Ime mora biti izmedju {min} i {max} broja slova.") String ime,
			@NotNull(message = "Prezime korisnika mora biti popunjeno") @Size(min = 3, max = 30, message = "Prezime mora biti izmedju {min} i {max} broja slova.") String prezime,
			@NotNull(message = "Password ne moze biti prazan") String password,
			@NotNull(message = "Potvrdite vasu sifru") String confirmedPassword,
			@NotNull(message = "Username ne moze biti prazan") @Size(min = 3, max = 30, message = "Username mora biti izmedju {min} i {max} broja slova.") String username,
			Integer version, @Email String email, @NotNull(message = "Uloga ne moze biti prazna") Uloga uloga,
			OdeljenjeEntity odeljenje, RoditeljEntity roditelj) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.password = password;
		this.confirmedPassword = confirmedPassword;
		this.username = username;
		this.version = version;
		this.email = email;
		this.uloga = uloga;
		this.odeljenje = odeljenje;
		this.roditelj = roditelj;
	}
	public RoditeljEntity getRoditelj() {
		return roditelj;
	}
	public void setRoditelj(RoditeljEntity roditelj) {
		this.roditelj = roditelj;
	}
	public OdeljenjeEntity getOdeljenje() {
		return odeljenje;
	}
	public void setOdeljenje(OdeljenjeEntity odeljenje) {
		this.odeljenje = odeljenje;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmedPassword() {
		return confirmedPassword;
	}
	public void setConfirmedPassword(String confirmedPassword) {
		this.confirmedPassword = confirmedPassword;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public KorisnikDTO(Long id,
			@NotNull(message = "Ime korisnika mora biti popunjeno") @Size(min = 3, max = 30, message = "Ime mora biti izmedju {min} i {max} broja slova.") String ime,
			@NotNull(message = "Prezime korisnika mora biti popunjeno") @Size(min = 3, max = 30, message = "Prezime mora biti izmedju {min} i {max} broja slova.") String prezime,
			@NotNull(message = "Password ne moze biti prazan") String password,
			@NotNull(message = "Potvrdite vasu sifru") String confirmedPassword,
			@NotNull(message = "Username ne moze biti prazan") @Size(min = 3, max = 30, message = "Username mora biti izmedju {min} i {max} broja slova.") String username,
			Integer version, @Email String email) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.password = password;
		this.confirmedPassword = confirmedPassword;
		this.username = username;
		this.version = version;
		this.email = email;
	}
	public KorisnikDTO() {
		super();
	}
	public Uloga getUloga() {
		return uloga;
	}

	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
	}
	public KorisnikDTO(Long id,
			@NotNull(message = "Ime korisnika mora biti popunjeno") @Size(min = 3, max = 30, message = "Ime mora biti izmedju {min} i {max} broja slova.") String ime,
			@NotNull(message = "Prezime korisnika mora biti popunjeno") @Size(min = 3, max = 30, message = "Prezime mora biti izmedju {min} i {max} broja slova.") String prezime,
			@NotNull(message = "Password ne moze biti prazan") String password,
			@NotNull(message = "Potvrdite vasu sifru") String confirmedPassword,
			@NotNull(message = "Username ne moze biti prazan") @Size(min = 3, max = 30, message = "Username mora biti izmedju {min} i {max} broja slova.") String username,
			Integer version, @Email String email, Uloga uloga) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.password = password;
		this.confirmedPassword = confirmedPassword;
		this.username = username;
		this.version = version;
		this.email = email;
		this.uloga = uloga;
	}
	
	
}
