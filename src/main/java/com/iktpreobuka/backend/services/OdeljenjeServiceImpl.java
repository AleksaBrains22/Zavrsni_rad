package com.iktpreobuka.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.iktpreobuka.backend.dto.OdeljenjeDTO;
import com.iktpreobuka.backend.entities.OdeljenjeEntity;
import com.iktpreobuka.backend.repositories.OdeljenjeRepositories;

@Service
public class OdeljenjeServiceImpl implements OdeljenjeService{
	@Autowired
	private OdeljenjeRepositories  odeljenjeRepositories;
	
	
	@Override
	public ResponseEntity<?> novoOdeljeEntity(OdeljenjeDTO novoOdeljenjeDTO) {
			OdeljenjeEntity odeljenje = new OdeljenjeEntity();
			odeljenje.setName(novoOdeljenjeDTO.getName());
			odeljenje.setRazred(novoOdeljenjeDTO.getRazred());
			odeljenjeRepositories.save(odeljenje);
		return new ResponseEntity<>(odeljenje , HttpStatus.OK);
	}

}
