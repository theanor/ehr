package be.monolith.ehr.iam.controller;

import javax.annotation.security.PermitAll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import be.monolith.ehr.iam.AuthenticationServiceException;
import be.monolith.ehr.iam.IamService;
import be.monolith.ehr.iam.Token;
import be.monolith.ehr.iam.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class IamServiceController implements IamService {

	@Autowired
	private AuthenticationService authenticationService;

	@PermitAll
	@Override
	public Token login(String username, String password) throws AuthenticationServiceException {
		return authenticationService.login(username, password);
	}

	@PermitAll
	@Override
	public Token check(String token) throws AuthenticationServiceException {
		return authenticationService.check(token);
	}

	@PermitAll
	@Override
	public void logout(String token) throws AuthenticationServiceException {
		authenticationService.logout(token);
	}

	@ExceptionHandler({ AuthenticationServiceException.class })
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public void handleException(AuthenticationServiceException e) {
		log.debug("authentication failed", e);

	}

}
