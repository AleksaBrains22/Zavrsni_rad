package com.iktpreobuka.backend.services;



import com.iktpreobuka.backend.dto.KorisnikDTO;
import com.iktpreobuka.backend.entities.KorisnikEntity;

public interface KorisnikService {

	public KorisnikEntity createNewUser(KorisnikDTO noviKorisnik);
	public KorisnikEntity findUserbyId(Long id);
	public KorisnikEntity brisanjeKorisnika(Long id);
	public KorisnikEntity updateKorisnika(KorisnikDTO updejtovanKorisnik, Long id);

}
