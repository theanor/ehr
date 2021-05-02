package be.monolith.ehr.parties.jpa;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import be.monolith.ehr.text.ClassMessages;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Embeddable
@Getter
@Setter
@Accessors(chain = true)
public class PersonName {

	public static final int MAX_TEXT_LENGTH = be.monolith.ehr.parties.dto.PersonName.MAX_TEXT_LENGTH;
	public static final int MAX_LAST_NAME_LENGTH = be.monolith.ehr.parties.dto.PersonName.MAX_LAST_NAME_LENGTH;
	public static final int MAX_FIRST_NAME_LENGTH = be.monolith.ehr.parties.dto.PersonName.MAX_FIRST_NAME_LENGTH;
	public static final int MAX_PREFIX_LENGTH = be.monolith.ehr.parties.dto.PersonName.MAX_PREFIX_LENGTH;
	public static final int MAX_SUFFIX_LENGTH = be.monolith.ehr.parties.dto.PersonName.MAX_SUFFIX_LENGTH;

	/**
	 * use of name
	 */
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private Use use;

	/**
	 * comma separated list of prefixes (e.g. "Dr", "Pr", "Sir")
	 */
	@Basic
	@Column(nullable = true, length = MAX_PREFIX_LENGTH)
	private String prefixes;

	/**
	 * comma separated list of first name(s)
	 */
	@Basic
	@Column(nullable = false, length = MAX_FIRST_NAME_LENGTH)
	private String firstNames;

	/**
	 * last (usually family) name
	 */
	@Basic
	@Column(nullable = false, length = MAX_LAST_NAME_LENGTH)
	private String lastName;

	/**
	 * comma separated list of suffixes (eg "Jr.", "Lic.")
	 */
	@Basic
	@Column(nullable = true, length = MAX_SUFFIX_LENGTH)
	private String suffixes;

	/**
	 * Time period when name was/is in use
	 */
	@Embedded
	private Period period;

	public enum Use {
		USUAL, OFFICIAL, NICKNAME, ANONYMOUS, MAIDEN, TEMP;

		@Override
		public String toString() {
			return ClassMessages.getString(this.getClass(), super.toString());
		}

	}
}
