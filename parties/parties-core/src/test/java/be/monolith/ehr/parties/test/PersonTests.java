package be.monolith.ehr.parties.test;

import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import be.monolith.ehr.parties.dto.Person;
import be.monolith.ehr.value.Identifier;
import be.monolith.ehr.value.Instant;
import be.monolith.ehr.value.Instant.Precision;
import be.monolith.ehr.value.Period;
import be.monolith.ehr.value.validation.MinimumInstantPrecision;

public class PersonTests {

	private ValidatorFactory validatorFactory;
	private Validator validator;

	@Before
	public void createValidator() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}

	@After
	public void closeValidationFactory() {
		validatorFactory.close();
	}

	private static class TestBean {
		@MinimumInstantPrecision(Precision.YEAR)
		private Instant i = new Instant(Precision.UNKNOWN, new Date());
	}

	@Test
	public void testDeathDatePrecision() {

		Person p = new Person();
		p.setBirthDate(new Instant(Precision.UNKNOWN, new Date()));
		p.setLanguages(Arrays.asList("fr"));
		p.setAdministrativeGender(Person.AdministrativeGender.MAN);
		p.setSource("mysource");
		p.setIdentifiers(Set.of(new Identifier("issuer", "scheme", "value", Period.AT_ALL_TIMES)));

		p.setDeathDate(new Instant(Precision.UNKNOWN, new Date()));

		Set<ConstraintViolation<Person>> violations = validator.validate(p);
		System.out.println(violations);
		assertFalse(violations.isEmpty());
	}
}
