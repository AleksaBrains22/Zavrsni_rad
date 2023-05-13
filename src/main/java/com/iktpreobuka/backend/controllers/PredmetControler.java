package com.iktpreobuka.backend.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.backend.dto.PredmetDTO;
import com.iktpreobuka.backend.entities.PredmetEntity;
import com.iktpreobuka.backend.services.PredmetServiceImpl;

@RestController
@RequestMapping(path = "api/v1/predmet")
@CrossOrigin(origins = "http://localhost:3000")
public class PredmetControler {
	@Autowired
	private PredmetServiceImpl predmetServiceImpl;
	private final Logger logger = (Logger)LoggerFactory.getLogger(this.getClass());
	
//	@Secured(value = { "ADMIN" })
	@RequestMapping(method = RequestMethod.POST, path = "/novipredmet")
	ResponseEntity<?> noviPredmet(@Valid @RequestBody PredmetDTO noviPredmetDTO) {
		PredmetEntity noviPredmet = predmetServiceImpl.novipredmet(noviPredmetDTO);
		logger.info("napravljen je novi predmet");
		return new ResponseEntity<>(noviPredmet, HttpStatus.CREATED);
	}
//	@Secured(value = { "ADMIN" })
	@RequestMapping(method = RequestMethod.DELETE, path = "/obrisipredmet/{id}")
	ResponseEntity<?> obrisiPredmet(@PathVariable Long id) {
		try {
			PredmetEntity obrisiPredmet = predmetServiceImpl.brisanjePredmeta(id);
			if (obrisiPredmet != null)
				logger.info("obrisan je predmet");
				return new ResponseEntity<>(obrisiPredmet, HttpStatus.ACCEPTED);
		} catch (Exception e) {
		}
		logger.warn("Predmet ne postoji ili je povezan u bazi sa drugim entitetom");
		return new ResponseEntity<>("Predmet je obrisan ili nije dobar id", HttpStatus.INTERNAL_SERVER_ERROR);
	}
//	@Secured(value = { "ADMIN" })
	@RequestMapping(method = RequestMethod.PUT, path = "/izmenaPredmeta/{id}")
	ResponseEntity<?> izmenaPredmeta(@Valid @RequestBody PredmetDTO noviPredmetDTO, @PathVariable Long id) {
		try {
			PredmetEntity updejtujPredmet = predmetServiceImpl.izmenaPredmeta(noviPredmetDTO, id);
			if (updejtujPredmet != null)
				logger.info("predmet je izmenjen");
				return new ResponseEntity<>(updejtujPredmet, HttpStatus.ACCEPTED);
		} catch (Exception e) {

		}
		logger.warn("Ne postoji predmet sa ovim idjem");
		return new ResponseEntity<>("Ne postoji predmet sa ovim idjem", HttpStatus.INTERNAL_SERVER_ERROR);
	}
//	@Secured(value = { "ADMIN" , "NASTAVNIK"})
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	ResponseEntity<?> pronadjiPredmetPoIdju(@PathVariable Long id) {
		try {
			PredmetEntity pronadjiPredmetPoIdju = predmetServiceImpl.pronadjiPredmetPoIdju(id);
			if (pronadjiPredmetPoIdju != null)
				logger.info("predmet je prodnadjen");
				return new ResponseEntity<>(pronadjiPredmetPoIdju, HttpStatus.OK);
		} catch (Exception e) {

		}
		logger.warn("Ne postoji predmet sa ovim idjem");
		return new ResponseEntity<>("Ne postoji predmet sa ovim idjem", HttpStatus.INTERNAL_SERVER_ERROR);
	}
//	@Secured(value = { "ADMIN","NASTAVNIK" })
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<?> pronadjiSvePredmete() {
		try {
			Iterable<PredmetEntity> sviPredmeti = predmetServiceImpl.pronadjiSvePredmete();
			if (sviPredmeti != null)
				logger.info("predmeti su pronadjeni");
				return new ResponseEntity<>(sviPredmeti, HttpStatus.OK);
		} catch (Exception e) {
		}
		logger.warn("Nema predmeta");
		return new ResponseEntity<>("Nema predmeta", HttpStatus.BAD_REQUEST);
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