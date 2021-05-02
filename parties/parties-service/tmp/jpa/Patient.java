package be.monolith.ehr.parties.jpa;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
public class Patient extends Person {

	@Temporal(TemporalType.DATE)
	private Date deathDate;

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private DatePrecision deathDatePrecision;

	@Basic
	@Column(nullable = false)
	private boolean alive;

}
