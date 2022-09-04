package be.monolith.ehr.parties.test;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import be.monolith.ehr.parties.dto.Person;
import be.monolith.ehr.parties.dto.PersonName;
import be.monolith.ehr.parties.dto.PostalAddress;
import be.monolith.ehr.parties.text.PersonAgeFormatter;
import be.monolith.ehr.parties.text.PersonNameFormatter;
import be.monolith.ehr.parties.text.PostalAddressFormatter;
import be.monolith.ehr.value.Instant;
import be.monolith.ehr.value.Instant.Precision;

public class LocaleTests {

	private Locale savedLocale;

	@Before
	public void saveLocale() {
		this.savedLocale = Locale.getDefault();
	}

	@After
	public void restoreLocale() {
		Locale.setDefault(savedLocale);
	}

	@Test
	public void GenderTests() {
		Locale.setDefault(Locale.ENGLISH);
		assertEquals("man", Person.AdministrativeGender.MAN.toString());
		assertEquals("woman", Person.AdministrativeGender.WOMAN.toString());
		Locale.setDefault(Locale.FRENCH);
		assertEquals("homme", Person.AdministrativeGender.MAN.toString());
		assertEquals("femme", Person.AdministrativeGender.WOMAN.toString());
	}

	@Test
	public void PostalAddressTests() {

		Locale.setDefault(new Locale.Builder().setRegion("BE").setLanguage("FR").build());
		PostalAddress address = new PostalAddress().setLines(Arrays.asList("Robert Barbiaux"))
				.setStreet("rue de la Wallonie").setNumber("13").setPostCode("7130").setCity("Hellebecq")
				.setCountry("BE");

		PostalAddressFormatter formatter = new PostalAddressFormatter();
		System.out.println(formatter.format(address));

	}

	@Test
	public void PersonNameTests() {
		PersonName personName = new PersonName().setGivenNames("Robert", "Henri").setFamily("Barbiaux")
				.setPrefixes("Mr.");
		PersonNameFormatter formatter = new PersonNameFormatter();
		assertEquals("Mr. Barbiaux Robert", formatter.format(personName, PersonName.Component.PREFIXES,
				PersonName.Component.FAMILY_NAME, PersonName.Component.FIRST_GIVEN));

	}

	@Test
	public void PersonAgeTests() throws ParseException {
		PersonAgeFormatter paf = new PersonAgeFormatter(
				Clock.fixed(java.time.Instant.parse("1967-10-18T23:00:10Z"), ZoneId.systemDefault()));
		Person person = new Person()
				.setBirthDate(new Instant(Precision.DAY, new SimpleDateFormat("yyyy-MM-dd").parse("1967-10-13")));
		Locale.setDefault(Locale.FRENCH);
		System.out.println(paf.format(person));

	}
}
