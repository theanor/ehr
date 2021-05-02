package be.monolith.ehr.text;

import java.text.ParseException;

public interface Parser<T> {

	boolean matches(String string);

	T parse(String string) throws ParseException;

}
