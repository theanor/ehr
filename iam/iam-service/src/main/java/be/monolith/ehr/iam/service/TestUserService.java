package be.monolith.ehr.iam.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TestUserService implements UserDetailsService {

	private String username = "foox";
	private String password = "barx";

	public void setTestUser(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new User(this.username, this.password, new ArrayList<>());
	}

}
