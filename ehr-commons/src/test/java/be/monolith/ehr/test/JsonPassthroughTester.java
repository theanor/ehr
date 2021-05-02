package be.monolith.ehr.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonPassthroughTester {
	public static <T> boolean test(Class<T> clazz, T marshalled)
			throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			objectMapper.writeValue(bos, marshalled);
			try (ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray())) {
				T unmarshalled = objectMapper.readValue(bis, clazz);
				return marshalled.equals(unmarshalled);
			}
		}
	}

}
