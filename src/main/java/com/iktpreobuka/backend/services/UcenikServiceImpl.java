package com.iktpreobuka.backend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.backend.entities.UcenikEntity;
import com.iktpreobuka.backend.repositories.UcenikRepositories;
@Service
public class UcenikServiceImpl implements UcenikService{
	@Autowired
	private UcenikRepositories ucenikRepositories;
	private final Logger logger = (Logger)LoggerFactory.getLogger(this.getClass());
	
	
	@Override
	public UcenikEntity nadjiUceikiaPoIdju(Long ucenikId) {
		logger.info("poslat je zahtev za nalazenje ucenika po idju");
		return ucenikRepositories.findById(ucenikId).get();

	}

}
