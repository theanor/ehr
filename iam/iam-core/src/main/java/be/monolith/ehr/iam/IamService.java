package be.monolith.ehr.iam;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(IamServiceConstants.SERVICE_ID)
public interface IamService {

	@GetMapping({ IamServiceConstants.LOGIN_PATH })
	Token login(@RequestParam(name = "username", required = true) String username,
			@RequestParam(name = "password", required = true) String password) throws AuthenticationServiceException;

	@GetMapping({ IamServiceConstants.CHECK_PATH })
	Token check(@RequestParam(name = "token", required = true) String token) throws AuthenticationServiceException;

	@GetMapping({ IamServiceConstants.LOGOUT_PATH })
	void logout(@RequestParam(name = "token", required = true) String token) throws AuthenticationServiceException;

}
