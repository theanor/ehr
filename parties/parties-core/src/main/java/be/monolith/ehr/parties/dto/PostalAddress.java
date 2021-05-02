package be.monolith.ehr.parties.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import be.monolith.ehr.text.ClassMessages;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Accessors(chain = true)
public class PostalAddress extends Address {

	public static final int MAX_STREET_LENGTH = 40;
	public static final int MAX_NUMBER_LENGTH = 10;
	public static final int MAX_POST_CODE_LENGTH = 16;
	public static final int MAX_CITY_LENGTH = 40;
	public static final int MAX_LINES_LENGTH = 200;

	@NotNull
	private Use use;

	@Size(min = 1)
	@NotNull
	private List<String> lines = new ArrayList<>();

	private String street;

	private String number;

	private String postCode;

	private String city;

	/* ISO 3166-1 alpha-2 country code */
	@NotNull
	private String country;

	public enum Use {
		HOME, WORK, TEMP, BILLING;

		@Override
		public String toString() {
			return ClassMessages.getString(this.getClass(), super.toString());
		}

	}

}
