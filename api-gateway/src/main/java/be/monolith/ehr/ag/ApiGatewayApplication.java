package be.monolith.ehr.ag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import be.monolith.ehr.ag.filter.AuthenticationFilter;
import be.monolith.ehr.iam.IamService;
import io.undertow.server.handlers.RequestDumpingHandler;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients(basePackages = { "be.monolith.ehr.iam", "be.monolith.ehr.parties" })
@ComponentScan("be.monolith.ehr.iam")
public class ApiGatewayApplication {

	@Autowired
	private IamService iamService;

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public UndertowServletWebServerFactory undertowServletWebServerFactory() {
		UndertowServletWebServerFactory factory = new UndertowServletWebServerFactory();
		factory.addDeploymentInfoCustomizers(deploymentInfo -> deploymentInfo.addInitialHandlerChainWrapper(handler -> {
			return new RequestDumpingHandler(handler);
		}));

		return factory;
	}

	@Bean
	public AuthenticationFilter authenticationFilter() {
		return new AuthenticationFilter(iamService);
	}

}
