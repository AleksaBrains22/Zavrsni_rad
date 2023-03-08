package com.iktpreobuka.backend.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.backend.dto.KorisnikDTO;
import com.iktpreobuka.backend.entities.KorisnikEntity;
import com.iktpreobuka.backend.services.KorisnikServiceImpl;

@RestController
@RequestMapping(path = "/api/v1/korisnik")
public class KorisnikControler {
	@Autowired
	private KorisnikServiceImpl korisnikServiceImpl;
	private final Logger logger = (Logger)LoggerFactory.getLogger(this.getClass());
	
	@Secured(value = { "ADMIN" })
	@RequestMapping(method = RequestMethod.POST, path = "/newUser")
	public ResponseEntity<KorisnikDTO> newUser(@Valid @RequestBody KorisnikDTO newKorisnikEntity) {
		try {
		korisnikServiceImpl.createNewUser(newKorisnikEntity);
		logger.info("Novi korisnik je napravljen");
		}catch (Exception e) {
			logger.error("Doslo je do greske pri pravljenju korisnika", e.getMessage());
		}
		return new ResponseEntity<KorisnikDTO>(newKorisnikEntity, HttpStatus.CREATED);
	}
	
	@Secured(value = { "ADMIN" })
	@RequestMapping(method = RequestMethod.PUT, path = "/updejtovanKorisnik/{id}")
	public ResponseEntity<?> updejtujKorisnika(@Valid @RequestBody KorisnikDTO updejtovanKorisnik,@PathVariable Long id) {
		try {
			logger.info("Korisnik je promenjen");
		KorisnikEntity korisnik =korisnikServiceImpl.updateKorisnika(updejtovanKorisnik, id);
		return new ResponseEntity<>(korisnik, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Korisnik sa tim Id-jem ne postoji");
			return new ResponseEntity<>("Pogresan unos ID-ja", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Secured(value = { "ADMIN" })
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public ResponseEntity<?> findUserById(@PathVariable Long id) {
		try {
			logger.info("korinik je preuzet");
			KorisnikEntity korisnik = korisnikServiceImpl.findUserbyId(id);
			return new ResponseEntity<>(korisnik, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("korinikci id je prazan");
			return new ResponseEntity<>("korisnik ne postoji", HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		
	}
	@Secured(value = { "ADMIN" })
	@RequestMapping(method = RequestMethod.DELETE, path = "/obrisiKorisnika/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		try {
			logger.info("Je obrisan");
			KorisnikEntity korinik = korisnikServiceImpl.brisanjeKorisnika(id);
			return new ResponseEntity<>(korinik, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			logger.error("Nema korisnika ili je vec obrisan sa tim id-jem");
			return new ResponseEntity<>("korisnik ne postoji ili je vec obrisan", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleException(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = "";
			String errorMessage = "";
			if (error instanceof FieldError) {
				fieldName = ((FieldError) error).getField();
				errorMessage = ((FieldError) error).getDefaultMessage();
			} else if (error instanceof ObjectError) {
				fieldName = ((ObjectError) error).getObjectName();
				errorMessage = error.getDefaultMessage();

			}
			errors.put(fieldName, errorMessage);
		});

		return errors;
	}
}
