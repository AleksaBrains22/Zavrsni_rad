package com.iktpreobuka.backend.services;


import com.iktpreobuka.backend.entities.Uloga;
import com.iktpreobuka.backend.entities.UlogaEntity;

public interface UlogaService {

	public Iterable<UlogaEntity> IzlistajSveUloge();

	public UlogaEntity ulogePoIdju(Integer id);

	public Boolean obrisiUloge(Integer id);
	
	public UlogaEntity findByUloga(Uloga uloga);

}
