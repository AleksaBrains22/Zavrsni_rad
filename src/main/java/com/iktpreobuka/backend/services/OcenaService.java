package com.iktpreobuka.backend.services;


import com.iktpreobuka.backend.entities.OcenaEntity;


public interface OcenaService {

	public OcenaEntity dodajNovuOcenu(Long ucenikId, Long predmetId, Long nastavnikId, OcenaEntity ocena);
}
