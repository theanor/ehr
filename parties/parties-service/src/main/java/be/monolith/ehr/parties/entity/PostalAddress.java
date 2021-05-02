package be.monolith.ehr.parties.entity;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostalAddress extends Address {

	public static final int MAX_STREET_LENGTH = 40;
	public static final int MAX_NUMBER_LENGTH = 10;
	public static final int MAX_POST_CODE_LENGTH = 16;
	public static final int MAX_CITY_LENGTH = 40;
	public static final int MAX_LINES_LENGTH = 200;

	@NotNull
	private String use;

	@Size(min = 1)
	@Length(max = MAX_LINES_LENGTH)
	@NotNull
	private List<String> lines = new ArrayList<>();

	@Length(max = MAX_STREET_LENGTH)
	private String street;

	@Length(max = MAX_NUMBER_LENGTH)
	private String number;

	@Length(max = MAX_POST_CODE_LENGTH)
	private String postCode;

	@Length(max = MAX_CITY_LENGTH)
	private String city;

	/* ISO 3166-1 alpha-2 country code */
	@NotNull
	@Length(min = 2, max = 2)
	private String country;

}
