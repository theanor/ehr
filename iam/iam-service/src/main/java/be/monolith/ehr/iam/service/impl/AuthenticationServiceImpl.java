package be.monolith.ehr.iam.service.impl;

import java.time.Clock;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import be.monolith.ehr.iam.AuthenticationServiceException;
import be.monolith.ehr.iam.Token;
import be.monolith.ehr.iam.service.AuthenticationService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

	// default token time to live is 2 hours
	private static final long DEFAULT_TOKEN_TTL = 1000 * 60 * 60 * 2;

	// default token refresh threshold is 10 minutes
	private static final long REFRESH_THRESHOLD = 1000 * 60 * 10;

	// TODO fetch this from a vault (java keystore or hashicorp)
	private static final String SECRET = "azfezafezafvc";

	@Autowired
	private AuthenticationManager authenticationManager;

	private Clock clock = Clock.systemUTC();

	private long tokenTtl = DEFAULT_TOKEN_TTL;

	// TODO check for concurrent structure
	private Set<String> invalidTokens = new HashSet<>();

	@Override
	public Token login(@NonNull String username, @NonNull String password) throws AuthenticationServiceException {

		Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
		try {
			authentication = authenticationManager.authenticate(authentication);
		} catch (AuthenticationException e) {
			log.info("failed login for {}", username);
			log.error("failed login exception", e);
			throw new AuthenticationServiceException("invalid credentials", e);
		}

		Claims claims = Jwts.claims();

		if (authentication.getAuthorities() != null) {
			authentication.getAuthorities().forEach(
					a -> claims.put(a.getAuthority() == null ? UUID.randomUUID().toString() : a.getAuthority(), a));
		}

		log.info("successful login for {}", username);

		return buildToken(authentication.getName(), claims);

	}

	private Token buildToken(String username, Claims claims) {
		long now = clock.millis();
		claims.put(Claims.SUBJECT, username);
		claims.put(Claims.ISSUED_AT, new Date(now));
		claims.put(Claims.EXPIRATION, new Date(now + tokenTtl));
		return new Token(
				Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, SECRET.getBytes()).compact(),
				claims);
	}

	@Override
	public Token check(@NonNull String token) throws AuthenticationServiceException {

		if (invalidTokens.contains(token)) {
			log.warn("received request with invalid token {}", token);
			throw new AuthenticationServiceException();
		}

		long now = clock.millis();

		try {

			Jwt<Header<?>, Claims> jwt = parseToken(token);
			Claims claims = jwt.getBody();
			if (claims.getExpiration().getTime() - now <= 0)
				throw new AuthenticationServiceException("token expired");
			if (claims.getExpiration().getTime() - now <= REFRESH_THRESHOLD) {
				return buildToken(claims.getSubject(), claims);
			}
			return new Token(token, claims);
		} catch (Exception e) {
			log.warn("exception occured during check", e);
			throw new AuthenticationServiceException("exception occured during check", e);
		}

	}

	@SuppressWarnings("unchecked")
	private Jwt<Header<?>, Claims> parseToken(String token) {
		return Jwts.parser().setSigningKey(SECRET.getBytes()).parse(token);
	}

	@Override
	public void logout(@NonNull String token) throws AuthenticationServiceException {
		try {
			parseToken(token); // check token validity to prevent filling up the list with invalid strings
			invalidTokens.add(token);
		} catch (Exception e) {
			log.warn("exception occured during logout", e);
			throw new AuthenticationServiceException("exception occured during logout", e);
		}
	}

}
