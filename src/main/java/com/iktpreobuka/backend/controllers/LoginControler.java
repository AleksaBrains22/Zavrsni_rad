//package com.iktpreobuka.backend.controllers;
//
//import java.sql.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import javax.crypto.SecretKey;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.iktpreobuka.backend.dto.KorisnikLogInDTO;
//import com.iktpreobuka.backend.entities.KorisnikEntity;
//import com.iktpreobuka.backend.repositories.KorisnikRepositories;
//import com.iktpreobuka.backend.util.PasswordEncryption;
//
//import io.jsonwebtoken.Jwts;
//
//@RestController
//@RequestMapping
//public class LoginControler {
//	@Autowired
//	private KorisnikRepositories korisnikRepositories;
//	@Autowired
//	private SecretKey secretKey;
//	@Value("${spring.security.token-duration}")
//	private Integer tokenDuration;
//	private final Logger logger = (Logger)LoggerFactory.getLogger(this.getClass());
//
//	
//	
//	private String getJWTToken(KorisnikEntity korisnikEntity ) {
//		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
//				.commaSeparatedStringToAuthorityList(korisnikEntity.getUloga().getUloga().toString());
//		String token = Jwts.builder().setId("softtekJWT").setSubject(korisnikEntity.getEmail())
//				.claim("authorities",
//						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
//				.setIssuedAt(new Date(System.currentTimeMillis()))
//				.setExpiration(new Date(System.currentTimeMillis() + this.tokenDuration)).signWith(this.secretKey)
//				.compact();
//		return "Bearer " + token;
//	}
//	
//
//	@RequestMapping(path = "/api/v1/login", method = RequestMethod.POST)
//	public ResponseEntity<?> login(@RequestParam("username") String username, @RequestParam("password") String pwd) {
//		KorisnikEntity korisnikEntity = korisnikRepositories.findByUsername(username);
//		if (korisnikEntity != null && PasswordEncryption.validatePassword(pwd, korisnikEntity.getPassword())) {
//			String token = getJWTToken(korisnikEntity);
//			KorisnikLogInDTO korisnik = new KorisnikLogInDTO();
//			korisnik.setUsername(username);
//			korisnik.setToken(token);
//			logger.info("Ulogovan je korisnik");
//			return new ResponseEntity<>(korisnik, HttpStatus.OK);
//		}
//		logger.error("Pogresan Username ili sifra");
//		return new ResponseEntity<>("Username ili sifra nije tacno unesena", HttpStatus.UNAUTHORIZED);
//	}
//	
//}
