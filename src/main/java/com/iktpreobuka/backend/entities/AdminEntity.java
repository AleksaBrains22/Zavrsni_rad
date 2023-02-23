package com.iktpreobuka.backend.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DiscriminatorFormula;

@Entity
@DiscriminatorValue(value = "Admin")
public class AdminEntity extends KorisnikEntity{

	public AdminEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminEntity(Long id,
			@NotNull(message = "Ime korisnika mora biti popunjeno") @Size(min = 3, max = 30, message = "Ime mora biti izmedju {min} i {max} broja slova.") String ime,
			@NotNull(message = "Prezime korisnika mora biti popunjeno") @Size(min = 3, max = 30, message = "Prezime mora biti izmedju {min} i {max} broja slova.") String prezime,
			@NotNull(message = "Password ne moze biti prazan") String password,
			@NotNull(message = "Username ne moze biti prazan") @Size(min = 3, max = 30, message = "Username mora biti izmedju {min} i {max} broja slova.") String username,
			Integer version, @Email String email, UlogaEntity uloga) {
		super(id, ime, prezime, password, username, version, email, uloga);
		// TODO Auto-generated constructor stub
	}

}
