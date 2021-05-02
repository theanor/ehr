package be.monolith.ehr.iam;

import java.util.Arrays;
import java.util.List;

public interface IamServiceConstants {

	String SERVICE_ID = "iam-service";

	String LOGIN_PATH = "/login";
	String LOGOUT_PATH = "/logout";
	String CHECK_PATH = "/check";
	String HEALTH_PATH = "/actuator/health";

	List<String> PATHS = Arrays.asList(LOGIN_PATH, LOGOUT_PATH, CHECK_PATH, HEALTH_PATH);

}
