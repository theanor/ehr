package be.monolith.ehr.parties.jpa;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import be.monolith.ehr.parties.dto.PostalAddress.Use;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Embeddable
public class PostalAddress extends Address {

	private static final int MAX_LINES_LENGTH = be.monolith.ehr.parties.dto.PostalAddress.MAX_LINES_LENGTH;
	private static final int MAX_STREET_LENGTH = be.monolith.ehr.parties.dto.PostalAddress.MAX_STREET_LENGTH;
	private static final int MAX_NUMBER_LENGTH = be.monolith.ehr.parties.dto.PostalAddress.MAX_NUMBER_LENGTH;
	private static final int MAX_CITY_LENGTH = be.monolith.ehr.parties.dto.PostalAddress.MAX_CITY_LENGTH;

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private Use use;

	@Basic
	@Column(nullable = false, length = MAX_LINES_LENGTH)
	private String[] lines;

	@Basic
	@Column(nullable = false, length = MAX_STREET_LENGTH)
	private String street;

	@Basic
	@Column(nullable = false, length = MAX_NUMBER_LENGTH)
	private String number;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "terminology", column = @Column(name = "POSTCODE_TERM", length = Code.MAX_TERM_LENGTH)),
			@AttributeOverride(name = "value", column = @Column(name = "POSTCODE_VALUE", length = Code.MAX_VALUE_LENGTH)) })
	private Code postCode;

	@Basic
	@Column(nullable = false, length = MAX_CITY_LENGTH)
	private String city;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "terminology", column = @Column(name = "COUNTRY_TERM", length = Code.MAX_TERM_LENGTH)),
			@AttributeOverride(name = "value", column = @Column(name = "COUNTRY_VALUE", length = Code.MAX_VALUE_LENGTH)) })
	private Code country;

}
