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

import com.iktpreobuka.backend.dto.OdeljenjeDTO;
import com.iktpreobuka.backend.entities.OdeljenjeEntity;
import com.iktpreobuka.backend.services.OdeljenjeServiceImpl;

@RestController
@RequestMapping(path = "api/v1/odeljenje")
public class OdeljenjeControler {
	@Autowired
	private OdeljenjeServiceImpl odeljenjeServiceImpl;

	@RequestMapping(method = RequestMethod.POST, value = "/novoOdeljnje/{id}")
	public ResponseEntity<?> novoOdeljenje(@Valid @RequestBody OdeljenjeDTO odeljenjeDTO, @PathVariable Long id) {
		try {
			OdeljenjeEntity odeljenje = odeljenjeServiceImpl.novoOdeljeEntity(odeljenjeDTO, id);
			if (odeljenje.getNastavnik() != null) 
				return new ResponseEntity<>(odeljenje, HttpStatus.CREATED);			
		} catch (Exception e) {
			return new ResponseEntity<>("Pogresan ID nastavnika", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return null;
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
