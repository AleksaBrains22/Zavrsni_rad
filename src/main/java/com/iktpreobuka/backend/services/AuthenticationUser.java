package com.iktpreobuka.backend.services;

import org.springframework.security.core.Authentication;

public interface AuthenticationUser {
	Authentication getAuthentication();
}
