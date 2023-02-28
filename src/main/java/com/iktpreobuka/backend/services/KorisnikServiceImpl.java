package com.iktpreobuka.backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.iktpreobuka.backend.dto.KorisnikDTO;
import com.iktpreobuka.backend.entities.AdminEntity;
import com.iktpreobuka.backend.entities.KorisnikEntity;
import com.iktpreobuka.backend.entities.NastavnikEntity;
import com.iktpreobuka.backend.entities.RoditeljEntity;
import com.iktpreobuka.backend.entities.UcenikEntity;
import com.iktpreobuka.backend.entities.Uloga;
import com.iktpreobuka.backend.entities.UlogaEntity;
import com.iktpreobuka.backend.repositories.KorisnikRepositories;

import com.iktpreobuka.backend.util.PasswordEncryption;

@Service
public class KorisnikServiceImpl implements KorisnikService {
	@Autowired	
	private KorisnikRepositories repositories;
	@Autowired
	private UlogaServiceImpl ulogaServiceImpl;
	@Autowired
	private RoditeljServiceImpl roditeljServiceImpl;

	
	
	@Override
	public KorisnikEntity createNewUser(KorisnikDTO noviKorisnik) {
		Uloga uloga = noviKorisnik.getUloga();
		switch (uloga) {
		case ADMIN:
			AdminEntity admin = new AdminEntity();
			UlogaEntity ulogaAdmin = ulogaServiceImpl.findByUloga(noviKorisnik.getUloga());
			admin.setIme(noviKorisnik.getIme());
			admin.setPrezime(noviKorisnik.getPrezime());
			admin.setUsername(noviKorisnik.getUsername());
			admin.setPassword(PasswordEncryption.getPassEncoded(noviKorisnik.getPassword()));
			admin.setEmail(noviKorisnik.getEmail());			
			admin.setUloga(ulogaAdmin);
			repositories.save(admin);
			return admin;
		case RODITELJ:
			RoditeljEntity roditelj = new RoditeljEntity();
			UlogaEntity ulogaRoditelj = ulogaServiceImpl.findByUloga(noviKorisnik.getUloga());
			roditelj.setIme(noviKorisnik.getIme());
			roditelj.setPrezime(noviKorisnik.getPrezime());
			roditelj.setUsername(noviKorisnik.getUsername());
			roditelj.setPassword(PasswordEncryption.getPassEncoded(noviKorisnik.getPassword()));
			roditelj.setEmail(noviKorisnik.getEmail());
			roditelj.setUloga(ulogaRoditelj);
			repositories.save(roditelj);
			return roditelj;
		case UCENIK:
	        UcenikEntity ucenik = new UcenikEntity();
	        UlogaEntity ulogaUcenik = ulogaServiceImpl.findByUloga(noviKorisnik.getUloga());
	        ucenik.setIme(noviKorisnik.getIme());
	        ucenik.setPrezime(noviKorisnik.getPrezime());
	        ucenik.setUsername(noviKorisnik.getUsername());
	        ucenik.setPassword(PasswordEncryption.getPassEncoded(noviKorisnik.getPassword()));
	        ucenik.setUloga(ulogaUcenik);

	        if (noviKorisnik.getOdeljenje() != null && noviKorisnik.getRoditelj() != null) {
	            RoditeljEntity roditeljUcenika = roditeljServiceImpl.nadjiRoditeljaPoIdju(noviKorisnik.getRoditelj().getId());
	            ucenik.setRoditelj(roditeljUcenika);
	            ucenik.setOdeljenjeEntity(noviKorisnik.getOdeljenje());
	        }

	        repositories.save(ucenik);
	        return ucenik;


		case NASTAVNIK:
			NastavnikEntity nastavnik = new NastavnikEntity();
			UlogaEntity ulogaNastavnik = ulogaServiceImpl.findByUloga(noviKorisnik.getUloga());
			nastavnik.setIme(noviKorisnik.getIme());
			nastavnik.setPrezime(noviKorisnik.getPrezime());
			nastavnik.setUsername(noviKorisnik.getUsername());
			nastavnik.setPassword(PasswordEncryption.getPassEncoded(noviKorisnik.getPassword()));
			nastavnik.setEmail(noviKorisnik.getEmail());
			nastavnik.setUloga(ulogaNastavnik);
			repositories.save(nastavnik);
			return nastavnik;
		}
		return null;
	}

	@Override
	public KorisnikEntity findUserbyId(Long id) {
		Optional<KorisnikEntity> korisnik = repositories.findById(id);
		return korisnik.get();
	}

	@Override
	public KorisnikEntity brisanjeKorisnika(Long id) {
		Optional<KorisnikEntity> korisnik = repositories.findById(id);
		if (korisnik.isPresent()) {
			repositories.delete(korisnik.get());

		}
		return korisnik.get();
	}

	@Override
	public KorisnikEntity updateKorisnika(KorisnikDTO updejtovanKorisnik, Long id) {
			Optional<KorisnikEntity> korisnik = repositories.findById(id);
			if(korisnik.isPresent()) {
				KorisnikEntity izmenjenKorisnik = korisnik.get();
				izmenjenKorisnik.setIme(updejtovanKorisnik.getIme());
				izmenjenKorisnik.setPrezime(updejtovanKorisnik.getPrezime());
				izmenjenKorisnik.setUsername(updejtovanKorisnik.getUsername());
				izmenjenKorisnik.setPassword(PasswordEncryption.getPassEncoded(updejtovanKorisnik.getPassword()));
				izmenjenKorisnik.setEmail(updejtovanKorisnik.getEmail());
		        UlogaEntity uloga = ulogaServiceImpl.findByUloga(updejtovanKorisnik.getUloga());
		        izmenjenKorisnik.setUloga(uloga);
		        repositories.save(izmenjenKorisnik);
		        return izmenjenKorisnik;
			}
			return null;
		}
}