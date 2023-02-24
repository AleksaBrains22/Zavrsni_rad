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

import com.iktpreobuka.backend.dto.KorisnikDTO;
import com.iktpreobuka.backend.services.KorisnikServiceImpl;

@RestController
@RequestMapping(path = "/api/v1/korisnik")
public class KorisnikControler {
	@Autowired
	private KorisnikServiceImpl korisnikServiceImpl;

	@RequestMapping(method = RequestMethod.POST, path = "/newUser")
	public ResponseEntity<?> newUser(@Valid @RequestBody KorisnikDTO newKorisnikEntity) {
		return korisnikServiceImpl.createNewUser(newKorisnikEntity);
	}
	@RequestMapping(method =RequestMethod.GET , path = "/{id}")
	public ResponseEntity<?> findUserById(@PathVariable Long id){
		return korisnikServiceImpl.findUserbyId(id);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path = "/obrisiKorisnika/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id){
		return korisnikServiceImpl.brisanjeKorisnika(id);
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
