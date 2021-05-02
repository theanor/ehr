package be.monolith.ehr.parties;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(initializers = PartiesServiceApplicationTests.MongoDbInitializer.class)
class PartiesServiceApplicationTests {

	private static MongoDbContainer mongoDbContainer;

	@BeforeAll
	public static void startContainerAndPublicPortIsAvailable() {
		mongoDbContainer = new MongoDbContainer();
		mongoDbContainer.start();
	}

	@Test
	void contextLoads() {
	}

	@Configuration
	public static class MongoDbInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

		@Override
		public void initialize(ConfigurableApplicationContext configurableApplicationContext) {

			TestPropertyValues values = TestPropertyValues.of(
					"spring.data.mongodb.host=" + mongoDbContainer.getContainerIpAddress(),
					"spring.data.mongodb.port=" + mongoDbContainer.getPort()

			);
			values.applyTo(configurableApplicationContext);
		}
	}

}
