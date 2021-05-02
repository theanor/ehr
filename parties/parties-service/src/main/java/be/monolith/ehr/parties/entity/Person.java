package be.monolith.ehr.parties.entity;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import be.monolith.ehr.value.Identifier;
import be.monolith.ehr.value.Instant;
import be.monolith.ehr.value.Instant.Precision;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Document
public class Person extends Party {

	@NotNull
	@Length(min = 1)
	@Indexed
	private Set<PersonName> names = new HashSet<>();

	@NotNull
	private String gender;

	@NotNull
	@Indexed
	private Instant birthDate;

	/**
	 * true if person is alive, false otherwise, null if status unknown
	 */
	private Boolean alive;

	/**
	 * administrative death date
	 */
	@be.monolith.ehr.value.validation.MinimumInstantPrecision(Precision.YEAR)
	private Instant deathDate;

	@Override
	public Person setIdentifiers(Set<Identifier> identifiers) {
		super.setIdentifiers(identifiers);
		System.out.println(identifiers);
		return this;
	}

}
