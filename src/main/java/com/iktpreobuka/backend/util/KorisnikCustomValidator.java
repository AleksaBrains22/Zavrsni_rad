package com.iktpreobuka.backend.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.iktpreobuka.backend.dto.KorisnikDTO;


@Component
public class KorisnikCustomValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return KorisnikDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		KorisnikDTO korisnik = (KorisnikDTO) target;
		if (korisnik.getPassword() != null && korisnik.getConfirmedPassword() != null  &&  !korisnik.getPassword().equals(korisnik.getConfirmedPassword())) {
			errors.reject("400", "Sifra se ne poklapa!");
		}
	}
}
