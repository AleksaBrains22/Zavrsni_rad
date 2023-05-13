//package com.iktpreobuka.backend.services;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//
//import com.iktpreobuka.backend.entities.KorisnikEntity;
//
//@Component
//public class AuthenticationUserImpl implements AuthenticationUser {
//	@Autowired
//	private KorisnikServiceImpl korisnikServiceImpl;
//	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
//
//	@Override
//	public Authentication getAuthentication() {
//		return SecurityContextHolder.getContext().getAuthentication();
//	}

//	public KorisnikEntity getAuthenticationByUsername(String username) {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if (authentication != null && authentication.isAuthenticated()) {
//			Object principal = authentication.getPrincipal();
//			if (principal instanceof KorisnikEntity) {
//				KorisnikEntity korisnik = (KorisnikEntity) principal;
//				if (korisnik.getUsername().equals(username)) {
//					logger.info("Korisnik sa ovim username-om je ulogovan");
//					return korisnik;
//				}
//			}
//		}
//
//		return null;
//	}

//	public boolean getAuthenticationById(Long korisnikId) {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if (authentication != null && authentication.isAuthenticated()) {
//			KorisnikEntity korisnik = korisnikServiceImpl.findUserbyId(korisnikId);
//			if (korisnik.getIme().equalsIgnoreCase(authentication.getName())) {
//				korisnik.getId().equals(korisnikId);
//				logger.info("Korisnik sa ovim id-om je ulogovan");
//				return true;			
//				}											
//		}
//
//		return false;
//	}
//
//}
