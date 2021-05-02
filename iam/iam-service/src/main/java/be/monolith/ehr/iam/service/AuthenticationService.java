package be.monolith.ehr.iam.service;

import org.springframework.lang.NonNull;

import be.monolith.ehr.iam.AuthenticationServiceException;
import be.monolith.ehr.iam.Token;

public interface AuthenticationService {

	Token login(@NonNull String username, @NonNull String password) throws AuthenticationServiceException;

	Token check(@NonNull String token) throws AuthenticationServiceException;

	void logout(@NonNull String token) throws AuthenticationServiceException;

}
