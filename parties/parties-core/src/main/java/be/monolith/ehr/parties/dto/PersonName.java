package be.monolith.ehr.parties.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import be.monolith.ehr.text.ClassMessages;
import be.monolith.ehr.value.Period;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Accessors(chain = true)
public class PersonName {

	public static final int MAX_TEXT_LENGTH = 82;
	public static final int MAX_LAST_NAME_LENGTH = 40;
	public static final int MAX_FIRST_NAME_LENGTH = 60;
	public static final int MAX_PREFIX_LENGTH = 20;
	public static final int MAX_SUFFIX_LENGTH = 20;
	public static final int MAX_PREFIXES_COUNT = 4;
	public static final int MAX_GIVEN_COUNT = 4;
	public static final int MAX_SUFFIXES_COUNT = 4;

	@NotNull
	private Use use;

	@Size(max = MAX_PREFIXES_COUNT)
	private String[] prefixes;

	@Size(max = MAX_GIVEN_COUNT)
	private String[] given;

	private String family;

	@Size(max = MAX_SUFFIXES_COUNT)
	private String[] suffixes;

	@NotNull
	private Period period;

	public enum Use {
		USUAL, OFFICIAL, NICKNAME, ANONYMOUS, MAIDEN, TEMP;

		@Override
		public String toString() {
			return ClassMessages.getString(this.getClass(), super.toString());
		}

	}

	public enum Component {
		PREFIXES, FIRST_GIVEN, GIVEN_NAMES, FAMILY_NAME, SUFFIXES;

		public String of(PersonName name) {
			switch (this) {
			case FIRST_GIVEN:
				return (name.getGiven() != null) && (name.getGiven().length > 0) ? name.getGiven()[0] : "";
			case GIVEN_NAMES:
				return String.join(" ", name.getGiven());
			case FAMILY_NAME:
				return name.getFamily();
			case PREFIXES:
				return name.getPrefixes() == null ? "" : String.join(" ", name.getPrefixes());
			case SUFFIXES:
				return name.getSuffixes() == null ? "" : String.join(" ", name.getSuffixes());
			default:
				return "";
			}
		}
	}

	public String getComponent(Component component) {
		return component.of(this);
	}

	public PersonName setGivenNames(String... givenNames) {
		this.given = givenNames;
		return this;
	}

	public PersonName setPrefixes(String... prefixes) {
		this.prefixes = prefixes;
		return this;
	}

	public PersonName setSuffixes(String... suffixes) {
		this.suffixes = suffixes;
		return this;
	}
}
