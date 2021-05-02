package be.monolith.ehr.parties.text;

import java.io.StringWriter;

import be.monolith.ehr.parties.dto.PersonName;
import be.monolith.ehr.text.AbstractFormatter;

public class PersonNameFormatter extends AbstractFormatter<PersonName> {

	private final static PersonName.Component[] DEFAULT_COMPONENTS = new PersonName.Component[] {
			PersonName.Component.FIRST_GIVEN, PersonName.Component.FAMILY_NAME };

	@Override
	public String format(PersonName object) {
		return format(object, DEFAULT_COMPONENTS);
	}

	public String format(PersonName object, PersonName.Component... components) {
		StringWriter sw = new StringWriter();
		for (PersonName.Component component : components) {
			sw.append(object.getComponent(component));
			sw.append(" ");
		}
		return sw.getBuffer().substring(0, sw.getBuffer().length() - 1);
	}

}
