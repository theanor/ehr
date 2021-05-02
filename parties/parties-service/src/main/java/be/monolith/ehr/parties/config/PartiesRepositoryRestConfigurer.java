package be.monolith.ehr.parties.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.MediaType;

import be.monolith.ehr.parties.convert.StringToIdentifierConverter;

@Configuration
public class PartiesRepositoryRestConfigurer implements RepositoryRestConfigurer {
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.setDefaultMediaType(MediaType.APPLICATION_JSON);
		config.useHalAsDefaultJsonMediaType(false);
	}

	@Override
	public void configureConversionService(ConfigurableConversionService conversionService) {
		conversionService.addConverter(new StringToIdentifierConverter());
	}

}