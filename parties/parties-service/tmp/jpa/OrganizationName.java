package be.monolith.ehr.parties.jpa;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import be.monolith.ehr.parties.dto.OrganizationName.Use;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Embeddable
public class OrganizationName {

	private static final int MAX_NAME_LENGTH = be.monolith.ehr.parties.dto.OrganizationName.MAX_NAME_LENGTH;

	@Basic
	@Column(nullable = false, length = MAX_NAME_LENGTH)
	private String name;

	@Embedded
	private Period period;

	@Enumerated(EnumType.ORDINAL)
	private Use use;

}
