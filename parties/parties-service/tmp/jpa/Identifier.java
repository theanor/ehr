package be.monolith.ehr.parties.jpa;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class Identifier {

	public static final int MAX_ISSUER_LENGTH = be.monolith.ehr.dto.value.Identifier.MAX_ISSUER_LENGTH;
	public static final int MAX_SCHEME_LENGTH = be.monolith.ehr.dto.value.Identifier.MAX_SCHEME_LENGTH;
	public static final int MAX_VALUE_LENGTH = be.monolith.ehr.dto.value.Identifier.MAX_VALUE_LENGTH;

	@Basic
	@Column(length = MAX_ISSUER_LENGTH, nullable = false)
	private String issuer;

	@Basic
	@Column(length = MAX_SCHEME_LENGTH, nullable = false)
	private String scheme;

	@Basic
	@Column(length = MAX_VALUE_LENGTH, nullable = false)
	private String value;

	@Embedded
	private Period period;

}
