package be.monolith.ehr.parties.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import be.monolith.ehr.text.ClassMessages;
import be.monolith.ehr.value.Instant;
import be.monolith.ehr.value.Instant.Precision;
import be.monolith.ehr.value.validation.MinimumInstantPrecision;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Accessors(chain = true)
public class Person extends Party {

	@NotNull
	private Set<PersonName> names = new HashSet<>();

	/**
	 * administrative sex (not gender, not biological sex)
	 */
	@NotNull
	private AdministrativeSex sex;

	@NotNull
	private Instant birthDate;

	/**
	 * true if person is alive, false otherwise, null if status unknown
	 */
	private Boolean alive;

	/**
	 * administrative death date, if known
	 */
	@MinimumInstantPrecision(Precision.YEAR)
	private Instant deathDate;

	public enum AdministrativeSex {
		MAN, WOMAN, OTHER, UNKNOWN;

		@Override
		public String toString() {
			return ClassMessages.getString(this.getClass(), super.toString());
		}
	}

}
