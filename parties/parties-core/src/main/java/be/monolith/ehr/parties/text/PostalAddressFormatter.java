package be.monolith.ehr.parties.text;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Locale;

import be.monolith.ehr.parties.dto.PostalAddress;
import be.monolith.ehr.text.AbstractFormatter;

public class PostalAddressFormatter extends AbstractFormatter<PostalAddress> {

	@Override
	public String format(PostalAddress address) {

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		for (String line : address.getLines()) {
			pw.println(line);
		}

		String number = address.getNumber() == null ? "" : address.getNumber();
		String street = address.getStreet() == null ? "" : address.getStreet();
		if ((number.length() > 0) || (street.length() > 0)) {
			if (locale.equals(Locale.FRANCE)) { // for France, place number before street
				pw.printf("%s %s%n", number, street);

			} else { // default is street followed by number
				pw.printf("%s %s%n", street, number);
			}
		}

		String postCode = address.getPostCode() == null ? "" : address.getPostCode();
		String city = address.getCity() == null ? "" : address.getCity();
		pw.printf("%s %s%n", postCode, city);

		if (!locale.getCountry().equalsIgnoreCase(address.getCountry())) {
			Locale countryLocale = new Locale.Builder().setRegion(address.getCountry()).build();
			pw.println(countryLocale.getDisplayCountry(locale));
		}

		return sw.toString();

	}

}
