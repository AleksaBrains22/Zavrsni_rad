package com.iktpreobuka.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.backend.entities.RoditeljEntity;
import com.iktpreobuka.backend.repositories.RoditeljRepositories;
@Service
public class RoditeljServiceImpl implements RoditeljService{
@Autowired
private RoditeljRepositories roditeljRepositories;
	
	
	@Override
	public RoditeljEntity nadjiRoditeljaPoIdju(Long roditeljId) {
		 RoditeljEntity roditelj = roditeljRepositories.findById(roditeljId).get();
		 return roditelj;
	}

}
