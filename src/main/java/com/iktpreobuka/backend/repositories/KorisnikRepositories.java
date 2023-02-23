package com.iktpreobuka.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import com.iktpreobuka.backend.entities.KorisnikEntity;

@Repository
public interface KorisnikRepositories extends CrudRepository<KorisnikEntity, Long> {
	public KorisnikEntity findByUsername(String username);
}
