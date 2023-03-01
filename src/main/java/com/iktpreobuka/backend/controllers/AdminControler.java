package com.iktpreobuka.backend.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin")
public class AdminControler {

	@RequestMapping(method = RequestMethod.GET, value = "/downloadLogs")
	public ResponseEntity<ByteArrayResource> downloadFile() throws java.io.IOException {

		HttpHeaders header = new HttpHeaders();
		header.add(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=spring-boot-logging.log");
		Path path1 = Paths.get("logs/spring-boot-logging.log");

		ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path1));

		return ResponseEntity.ok().headers(header)
				.contentType(org.springframework.http.MediaType.APPLICATION_OCTET_STREAM).body(resource);

	}

}
