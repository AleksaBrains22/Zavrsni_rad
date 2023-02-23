package com.iktpreobuka.backend.services;

import org.springframework.http.ResponseEntity;

import com.iktpreobuka.backend.dto.OdeljenjeDTO;

public interface OdeljenjeService {

	public ResponseEntity<?> novoOdeljeEntity(OdeljenjeDTO novoOdeljenjeDTO);
	
}
