package com.iktpreobuka.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.backend.entities.NastavnikEntity;
import com.iktpreobuka.backend.repositories.NastavnikRepositories;

@Service
public class NastavnikServiceImpl implements NastavnikService{

	@Autowired
	private NastavnikRepositories nastavnikRepositories;

	@Override
	public NastavnikEntity findById(Long id) {
		NastavnikEntity nastavnik = nastavnikRepositories.findById(id).get();	
		return nastavnik;
	}
	
	
	
	
	
	
}
