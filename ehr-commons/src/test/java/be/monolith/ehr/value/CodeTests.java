package be.monolith.ehr.value;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.junit.Test;

import be.monolith.ehr.test.JaxbPassthroughTester;
import be.monolith.ehr.test.JsonPassthroughTester;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

public class CodeTests {

	private static final String CODE_VALUE = "404684003 | Clinical finding (finding) |";
	private static final String TERM_ID = "SNOMED-CT";

	@Test
	public void testCode() throws URISyntaxException {
		Code c1 = new Code(TERM_ID, CODE_VALUE);
		Code c2 = new Code(TERM_ID, CODE_VALUE);
		assertEquals(c1, c2);
	}

	@Test
	public void bindingTest() throws JAXBException, IOException {
		TestClass tc = new TestClass();
		tc.setCode(new Code(TERM_ID, CODE_VALUE));
		assertTrue(JaxbPassthroughTester.test(TestClass.class, tc));
		assertTrue(JsonPassthroughTester.test(TestClass.class, tc));
	}

	@XmlRootElement(name = "test-class")
	@Getter
	@Setter
	@EqualsAndHashCode(callSuper = false)
	private static class TestClass {
		@XmlElement(name = "code")
		private Code code;
	}

}
