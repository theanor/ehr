package be.monolith.ehr.parties.jpa;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import be.monolith.ehr.parties.dto.TelecomAddress.System;
import be.monolith.ehr.parties.dto.TelecomAddress.Use;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Embeddable
public class TelecomAddress extends Address {

	private static final int MAX_VALUE_LENGTH = 30;

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private Use use;

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private System system;

	@Basic
	@Column(nullable = false, length = MAX_VALUE_LENGTH)
	private String value;

}
