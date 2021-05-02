package be.monolith.ehr.parties.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import be.monolith.ehr.value.Period;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
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
	private String use;

	@Length(max = MAX_PREFIX_LENGTH)
	@Size(max = MAX_PREFIXES_COUNT)
	private String[] prefixes;

	@Length(max = MAX_FIRST_NAME_LENGTH)
	@Size(max = MAX_GIVEN_COUNT)
	private String[] given;

	@Length(max = MAX_LAST_NAME_LENGTH)
	private String family;

	@Length(max = MAX_SUFFIX_LENGTH)
	@Size(max = MAX_SUFFIXES_COUNT)
	private String[] suffixes;

	@NotNull
	private Period period;

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
