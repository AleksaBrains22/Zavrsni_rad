package com.iktpreobuka.backend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.backend.entities.RoditeljEntity;
import com.iktpreobuka.backend.repositories.RoditeljRepositories;
@Service
public class RoditeljServiceImpl implements RoditeljService{
@Autowired
private RoditeljRepositories roditeljRepositories;
private final Logger logger = (Logger)LoggerFactory.getLogger(this.getClass());

	
	@Override
	public RoditeljEntity nadjiRoditeljaPoIdju(Long roditeljId) {
		 RoditeljEntity roditelj = roditeljRepositories.findById(roditeljId).get();
		 logger.info("poslat je zahtev za nalazenje roditelja po idju");
		 return roditelj;
	}

}
