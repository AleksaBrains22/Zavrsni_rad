package com.iktpreobuka.backend.services;


import java.util.List;

import com.iktpreobuka.backend.entities.OcenaEntity;
import com.iktpreobuka.backend.entities.Polugodiste;
import com.iktpreobuka.backend.entities.PredmetEntity;
import com.iktpreobuka.backend.entities.UcenikEntity;


public interface OcenaService {

	public OcenaEntity dodajNovuOcenu(Long ucenikId, Long predmetId, Long nastavnikId, OcenaEntity ocena);
	public List<OcenaEntity> nadjiSveOceneUcenikaizJednogPredmeta(UcenikEntity ucenikId , PredmetEntity predmetId);
	public List<OcenaEntity> nadjiOceneUcenikaIzSvihPredmeta(UcenikEntity ucenikId);
	public OcenaEntity izmeniOcenuUcenikaIzPredmeta(Long ocenaId,OcenaEntity novaOcena);
	public OcenaEntity izbrisiOcenuUcenika(Long ocenaId);
	public List<OcenaEntity> pronadjiPoUcenikuIPredmetiIPolugodistuOcenu(UcenikEntity ucenikId, PredmetEntity predmetId, String polugodiste);

}
