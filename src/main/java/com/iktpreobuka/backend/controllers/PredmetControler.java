package com.iktpreobuka.backend.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.iktpreobuka.backend.dto.PredmetDTO;
import com.iktpreobuka.backend.entities.PredmetEntity;
import com.iktpreobuka.backend.services.PredmetServiceImpl;

@RestController
@RequestMapping(path = "api/v1/predmet")
public class PredmetControler {
	@Autowired
	private PredmetServiceImpl predmetServiceImpl;

	@RequestMapping(method = RequestMethod.POST, path = "/novipredmet")
	ResponseEntity<?> noviPredmet(@Valid @RequestBody PredmetDTO noviPredmetDTO) {
		PredmetEntity noviPredmet = predmetServiceImpl.novipredmet(noviPredmetDTO);
		return new ResponseEntity<>(noviPredmet, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/obrisipredmet/{id}")
	ResponseEntity<?> obrisiPredmet(@PathVariable Long id) {
		try {
			PredmetEntity obrisiPredmet = predmetServiceImpl.brisanjePredmeta(id);
			if (obrisiPredmet != null)
				return new ResponseEntity<>(obrisiPredmet, HttpStatus.ACCEPTED);
		} catch (Exception e) {
		}
		return new ResponseEntity<>("Predmet je obrisan ili nije dobar id", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/izmenaPredmeta/{id}")
	ResponseEntity<?> izmenaPredmeta(@Valid @RequestBody PredmetDTO noviPredmetDTO, @PathVariable Long id) {
		try {
			PredmetEntity updejtujPredmet = predmetServiceImpl.izmenaPredmeta(noviPredmetDTO, id);
			if (updejtujPredmet != null)
				return new ResponseEntity<>(updejtujPredmet, HttpStatus.ACCEPTED);
		} catch (Exception e) {

		}
		return new ResponseEntity<>("Ne postoji predmet sa ovim idjem", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	ResponseEntity<?> pronadjiPredmetPoIdju(@PathVariable Long id) {
		try {
			PredmetEntity pronadjiPredmetPoIdju = predmetServiceImpl.pronadjiPredmetPoIdju(id);
			if (pronadjiPredmetPoIdju != null)
				return new ResponseEntity<>(pronadjiPredmetPoIdju, HttpStatus.OK);
		} catch (Exception e) {

		}
		return new ResponseEntity<>("Ne postoji predmet sa ovim idjem", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<?> pronadjiSvePredmete() {
		try {
			Iterable<PredmetEntity> sviPredmeti = predmetServiceImpl.pronadjiSvePredmete();
			if (sviPredmeti != null)
				return new ResponseEntity<>(sviPredmeti, HttpStatus.OK);
		} catch (Exception e) {
		}
		return new ResponseEntity<>("Nema predmeta", HttpStatus.INTERNAL_SERVER_ERROR);
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