package be.monolith.ehr.parties.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import be.monolith.ehr.value.Identifier;
import be.monolith.ehr.value.Period;

@Component
public class StringToIdentifierConverter implements Converter<String, Identifier> {

	@Override
	public Identifier convert(String source) {

		if (source == null)
			return null;

		String[] tokens = source.split("\\,");
		if (tokens.length != 3)
			return null;
		return new Identifier(tokens[0], tokens[1], tokens[2], Period.AT_ALL_TIMES);
	}

}
