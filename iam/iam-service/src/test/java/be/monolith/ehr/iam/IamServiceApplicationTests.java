package be.monolith.ehr.iam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import be.monolith.commons.test.spring.AbstractWebAppTest;
import be.monolith.ehr.iam.service.TestUserService;

@SpringBootTest
@EnableEurekaClient
class IamServiceApplicationTests extends AbstractWebAppTest {

	private static final String VALID_USERNAME = "foo";
	private static final String INVALID_PASSWORD = "sedraerf";
	private static final String VALID_PASSWORD = "bar";

	@Autowired
	private ApplicationContext applicationContext;

	@BeforeEach
	void setup() {
		TestUserService testUserService = applicationContext.getBean(TestUserService.class);
		testUserService.setTestUser(VALID_USERNAME, VALID_PASSWORD);
	}

	@Test
	void contextLoads() {
	}

	@Test
	String successfulLogin() throws Exception {

		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.get(IamServiceConstants.LOGIN_PATH).accept(MediaType.APPLICATION_JSON_VALUE)
						.param("username", VALID_USERNAME).param("password", VALID_PASSWORD))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

		String content = mvcResult.getResponse().getContentAsString();
		assertNotEquals(null, content);

		ObjectMapper objectMapper = new ObjectMapper(); // com.fasterxml.jackson.databind.ObjectMapper

		Token response = objectMapper.readValue(content, Token.class);

		return response.getValue();
	}

	@Test
	void failedLogin() throws Exception {

		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.get(IamServiceConstants.LOGIN_PATH).accept(MediaType.APPLICATION_JSON_VALUE)
						.param("username", VALID_USERNAME).param("password", INVALID_PASSWORD))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(401, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals("", content);
	}

	@Test
	void checkWithinTtl() throws Exception {

		String token = successfulLogin();

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(IamServiceConstants.CHECK_PATH)
				.accept(MediaType.APPLICATION_JSON_VALUE).param("token", token)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

		String content = mvcResult.getResponse().getContentAsString();
		assertNotEquals(null, content);

		ObjectMapper objectMapper = new ObjectMapper();

		Token response = objectMapper.readValue(content, Token.class);

		assertEquals(token, response.getValue());
	}

	@Test
	void checkWithinRenewalPerdiod() throws Exception {
	}

	@Test
	void checkExpiredToken() {

	}

	@Test
	void logout() throws Exception {

		String token = successfulLogin();

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(IamServiceConstants.LOGOUT_PATH)
				.accept(MediaType.APPLICATION_JSON_VALUE).param("token", token)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals("", content);

	}

}
