package com.iktpreobuka.backend.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.backend.entities.UcenikEntity;
import com.iktpreobuka.backend.entities.ZakljucnaOcenaEntity;


public interface ZakljucnaOcenaRepositories extends CrudRepository<ZakljucnaOcenaEntity, Long>{

	Optional<ZakljucnaOcenaEntity> findByUcenik(UcenikEntity ucenikId);
}
