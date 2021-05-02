package be.monolith.ehr.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JaxbPassthroughTester {

	public static <T> boolean test(Class<T> clazz, T marshalled) throws JAXBException, IOException {
		JAXBContext context = JAXBContext.newInstance(clazz);
		Marshaller marshaller = context.createMarshaller();
		Unmarshaller unmarshaller = context.createUnmarshaller();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		marshaller.marshal(marshalled, bos);
		bos.close();
		ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
		Object unmarshalled = unmarshaller.unmarshal(bis);
		bis.close();
		return unmarshalled.equals(marshalled);
	}
}
