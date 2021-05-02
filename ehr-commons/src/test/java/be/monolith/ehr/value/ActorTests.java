package be.monolith.ehr.value;

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

public class ActorTests {

	private static final String NAME = "Donald Duck";
	private static final Code ROLE = new Code("Foo", "Bar");
	private static final Identifier IDENTIFIER = new Identifier("FOO", "BAR", "foo_BAR123", Period.AT_ALL_TIMES);

	@Test
	public void bindingTest() throws JAXBException, IOException {
		TestClass tc = new TestClass();
		tc.setActor(new Actor(NAME, ROLE, IDENTIFIER));
		assertTrue(JaxbPassthroughTester.test(TestClass.class, tc));
		assertTrue(JsonPassthroughTester.test(TestClass.class, tc));
	}

	@XmlRootElement(name = "test-class")
	@Getter
	@Setter
	@EqualsAndHashCode(callSuper = false)
	private static class TestClass {
		@XmlElement(name = "actor")
		private Actor actor;
	}

}
