package com.iktpreobuka.backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.iktpreobuka.backend.dto.KorisnikDTO;
import com.iktpreobuka.backend.entities.AdminEntity;
import com.iktpreobuka.backend.entities.KorisnikEntity;
import com.iktpreobuka.backend.entities.NastavnikEntity;
import com.iktpreobuka.backend.entities.RoditeljEntity;
import com.iktpreobuka.backend.entities.UcenikEntity;
import com.iktpreobuka.backend.entities.Uloga;
import com.iktpreobuka.backend.repositories.KorisnikRepositories;
import com.iktpreobuka.backend.util.PasswordEncryption;

@Service
public class KorisnikServiceImpl implements KorisnikService {
	@Autowired
	private KorisnikRepositories repositories;

	@Override
	public ResponseEntity<?> createNewUser(KorisnikDTO noviKorisnik) {
		Uloga uloga = noviKorisnik.getUloga();
		switch (uloga) {
		case ADMIN:
			AdminEntity admin = new AdminEntity();
			admin.setIme(noviKorisnik.getIme());
			admin.setPrezime(noviKorisnik.getPrezime());
			admin.setUsername(noviKorisnik.getUsername());
			admin.setPassword(PasswordEncryption.getPassEncoded(noviKorisnik.getPassword()));
			admin.setEmail(noviKorisnik.getEmail());
			repositories.save(admin);
			return new ResponseEntity<AdminEntity>(admin, HttpStatus.OK);
		case RODITELJ:
			RoditeljEntity roditelj = new RoditeljEntity();
			roditelj.setIme(noviKorisnik.getIme());
			roditelj.setPrezime(noviKorisnik.getPrezime());
			roditelj.setUsername(noviKorisnik.getUsername());
			roditelj.setPassword(PasswordEncryption.getPassEncoded(noviKorisnik.getPassword()));
			roditelj.setEmail(noviKorisnik.getEmail());
			repositories.save(roditelj);
			return new ResponseEntity<RoditeljEntity>(roditelj, HttpStatus.OK);
		case UCENIK:
			UcenikEntity ucenik = new UcenikEntity();
			ucenik.setIme(noviKorisnik.getIme());
			ucenik.setPrezime(noviKorisnik.getPrezime());
			ucenik.setUsername(noviKorisnik.getUsername());
			ucenik.setPassword(PasswordEncryption.getPassEncoded(noviKorisnik.getPassword()));
			repositories.save(ucenik);
			return new ResponseEntity<UcenikEntity>(ucenik, HttpStatus.OK);
		case NASTAVNIK:
			NastavnikEntity nastavnik = new NastavnikEntity();
			nastavnik.setIme(noviKorisnik.getIme());
			nastavnik.setPrezime(noviKorisnik.getPrezime());
			nastavnik.setUsername(noviKorisnik.getUsername());
			nastavnik.setPassword(PasswordEncryption.getPassEncoded(noviKorisnik.getPassword()));
			nastavnik.setEmail(noviKorisnik.getEmail());
			repositories.save(nastavnik);
			return new ResponseEntity<NastavnikEntity>(nastavnik, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Korisnik sa tom ulogom ne postoji", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findUserbyId(Long id) {
		Optional<KorisnikEntity> korisnik = repositories.findById(id);
		if (!korisnik.isPresent()) {
			return new ResponseEntity<>("User sa tim ID brojem ne postoji", HttpStatus.BAD_REQUEST);
		}	
		return new ResponseEntity<>(korisnik,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> brisanjeKorisnika(Long id) {
		Optional<KorisnikEntity> korisnik = repositories.findById(id);
		if (korisnik.isPresent()) {
			repositories.delete(korisnik.get());
		return new ResponseEntity<>(korisnik,HttpStatus.OK);
		}
		return new ResponseEntity<>("User sa tim ID brojem ne postoji ili je vec obrisan", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}