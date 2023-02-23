package com.iktpreobuka.backend.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.backend.entities.Uloga;
import com.iktpreobuka.backend.entities.UlogaEntity;

public interface UlogaRepositories extends CrudRepository<UlogaEntity, Integer> {
UlogaEntity findByUloga(Uloga uloga);
}
