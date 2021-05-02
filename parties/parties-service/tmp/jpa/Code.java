package be.monolith.ehr.parties.jpa;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Embeddable
@Getter
@Setter
@Accessors(chain = true)
public class Code {

	public static final int MAX_TERM_LENGTH = be.monolith.ehr.dto.value.Code.MAX_TERM_LENGTH;
	public static final int MAX_VALUE_LENGTH = be.monolith.ehr.dto.value.Code.MAX_VALUE_LENGTH;

	@Basic
	@Column(length = MAX_TERM_LENGTH, nullable = false)
	private String terminology;

	@Basic
	@Column(length = MAX_VALUE_LENGTH, nullable = false)
	private String value;
}
