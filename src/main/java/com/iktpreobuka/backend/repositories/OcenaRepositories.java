package com.iktpreobuka.backend.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.backend.entities.OcenaEntity;
import com.iktpreobuka.backend.entities.Polugodiste;
import com.iktpreobuka.backend.entities.PredmetEntity;
import com.iktpreobuka.backend.entities.UcenikEntity;

public interface OcenaRepositories extends CrudRepository<OcenaEntity, Long> {
	public List<OcenaEntity> findByUcenik(UcenikEntity ucenikId);

	public List<OcenaEntity> findByUcenikAndPredmet(UcenikEntity ucenik, PredmetEntity predmet);

	public List<OcenaEntity> findByUcenikAndPredmetAndPolugodiste(UcenikEntity ucenik, PredmetEntity predmet, Polugodiste polugodiste);

}
