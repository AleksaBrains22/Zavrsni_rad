package com.iktpreobuka.backend.services;

import org.springframework.http.ResponseEntity;

import com.iktpreobuka.backend.dto.PredmetDTO;


public interface PredmetService {

	public ResponseEntity<?> novipredmet(PredmetDTO noviPredmetDTO);
	
}
