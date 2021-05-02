package be.monolith.ehr.it;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.cloud.openfeign.ribbon.FeignRibbonClientAutoConfiguration;

import be.monolith.ehr.iam.AuthenticationServiceException;
import be.monolith.ehr.iam.Token;

@SpringBootTest
@SpringBootConfiguration
@EnableFeignClients
@ImportAutoConfiguration({ RibbonAutoConfiguration.class, FeignRibbonClientAutoConfiguration.class,
		FeignAutoConfiguration.class })
public class DummyIT {

	@Autowired
	IamServiceClient iamClient;

	@Test
	public void test() throws AuthenticationServiceException {

		System.out.println("This is a integration test.==============");

//		IamService iamClient = Feign.builder().client(new OkHttpClient()).encoder(new GsonEncoder())
//				.decoder(new GsonDecoder()).logger(new Slf4jLogger(IamService.class)).logLevel(Level.FULL)
//				.target(IamService.class, "http://localhost:8443/api/auth");

		assertNotNull(iamClient);

		Token token = iamClient.login("foox", "barx");
		assertNotNull(token);

		System.out.println("token value : " + token.getValue());

	}

}
