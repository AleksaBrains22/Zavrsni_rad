package com.iktpreobuka.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.iktpreobuka.backend.dto.PredmetDTO;
import com.iktpreobuka.backend.entities.PredmetEntity;
import com.iktpreobuka.backend.repositories.PredmetRepositories;


@Service
public class PredmetServiceImpl implements PredmetService {

	@Autowired
	private PredmetRepositories predmetRepositories;

	@Override
	public ResponseEntity<?> novipredmet(PredmetDTO noviPredmetDTO) {
		PredmetEntity predmet = new PredmetEntity();
		predmet.setName(noviPredmetDTO.getName());
		predmet.setFondCasova(noviPredmetDTO.getFondCasova());
		predmet.setRazred(noviPredmetDTO.getRazred());
		predmetRepositories.save(predmet);
		return new ResponseEntity<>(predmet, HttpStatus.OK);
	}

}
