package be.monolith.ehr.parties.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import be.monolith.ehr.parties.dto.Person.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Entity
public class Person extends Party {

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(indexes = { @Index(columnList = "familyName") })
	private List<PersonName> names = new ArrayList<>();

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private Gender gender;

	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	private Date birthDate;

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private DatePrecision birthDatePrecision;

	public enum DatePrecision {
		DAY, MONTH, YEAR
	}

}
