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

import com.iktpreobuka.backend.entities.OcenaEntity;
import com.iktpreobuka.backend.services.OcenaServiceImpl;

@RestController
@RequestMapping("api/v1/ocena")
public class OcenaControler {
	@Autowired
	private OcenaServiceImpl ocenaServiceImpl;

	@RequestMapping(method = RequestMethod.POST, path = "/novaOcena/ucenika/{ucenikId}/iz/{predmetId}/od/nastavnika/{nastavnikId}")
	public ResponseEntity<?> novaOcena(@Valid @RequestBody OcenaEntity ocena, @PathVariable Long ucenikId,
			@PathVariable Long predmetId, @PathVariable Long nastavnikId) {
		try {
			OcenaEntity novaOcena = ocenaServiceImpl.dodajNovuOcenu(ucenikId, predmetId, nastavnikId, ocena);
			if(novaOcena != null) {
			return new ResponseEntity<>(novaOcena, HttpStatus.CREATED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Nije dobar zahtev", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Nije dobar zahtev", HttpStatus.INTERNAL_SERVER_ERROR);
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
