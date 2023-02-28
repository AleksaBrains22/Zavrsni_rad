package com.iktpreobuka.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.backend.entities.UcenikEntity;
import com.iktpreobuka.backend.repositories.UcenikRepositories;
@Service
public class UcenikServiceImpl implements UcenikService{
	@Autowired
	private UcenikRepositories ucenikRepositories;
	
	
	@Override
	public UcenikEntity nadjiUceikiaPoIdju(Long ucenikId) {
		return ucenikRepositories.findById(ucenikId).get();

	}

}
