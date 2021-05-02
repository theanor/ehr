package be.monolith.ehr.value;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.junit.Test;

import be.monolith.ehr.test.JaxbPassthroughTester;
import be.monolith.ehr.test.JsonPassthroughTester;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

public class CodedTextTests {

	private static final String TERM_ID = "foo";
	private static final String CODE_VALUE = "bar";

	private static final Code CODE = new Code(TERM_ID, CODE_VALUE);
	private static final Text TEXT = new Text("fr", "the quick brown fox jumped over the lazy dög".getBytes(),
			"text/plain");

	@Test
	public void equalsTest() {
		CodeableConcept c1 = new CodeableConcept(TEXT, CODE);
		CodeableConcept c2 = new CodeableConcept(TEXT, CODE);
		assertEquals(c1, c2);
	}

	@Test
	public void bindingTest() throws JAXBException, IOException {
		CodeableConcept c1 = new CodeableConcept(TEXT, CODE);
		TestClass tc = new TestClass();
		tc.setCodedText(c1);
		assertTrue(JaxbPassthroughTester.test(TestClass.class, tc));
		assertTrue(JsonPassthroughTester.test(TestClass.class, tc));

	}

	@XmlRootElement(name = "test-class")
	@Getter
	@Setter
	@EqualsAndHashCode(callSuper = false)
	private static class TestClass {

		@XmlElement(name = "code")
		CodeableConcept codedText;

	}

}
