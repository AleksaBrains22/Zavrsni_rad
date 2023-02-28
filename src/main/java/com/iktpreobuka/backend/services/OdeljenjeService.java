package com.iktpreobuka.backend.services;


import com.iktpreobuka.backend.dto.OdeljenjeDTO;
import com.iktpreobuka.backend.entities.OdeljenjeEntity;

public interface OdeljenjeService {

	public OdeljenjeEntity novoOdeljeEntity(OdeljenjeDTO novoOdeljenjeDTO , Long id);
	public OdeljenjeEntity nadjiOdeljenjePOIdju(Long odeljenjeId);
	
}
