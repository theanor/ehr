package be.monolith.ehr.value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A numeric or alphanumeric string that is associated with a single entity or
 * aggregate within a given system. Typically, identifiers are used as
 * references to those objects. Some identifiers may be changed or retired due
 * to human or system process and errors (hence @link Identifier#period).
 * 
 * @author rba
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
@ToString
public class Identifier {

	public static final int MAX_ISSUER_LENGTH = 32;
	public static final int MAX_SCHEME_LENGTH = 32;
	public static final int MAX_VALUE_LENGTH = 64;

	/**
	 * identifier issuer identifier
	 */
	@Size(max = MAX_ISSUER_LENGTH)
	@NotNull
	@JsonProperty(required = true)
	@XmlElement(required = true, nillable = false)
	private String issuer;

	/**
	 * identifier scheme
	 */
	@Size(max = MAX_SCHEME_LENGTH)
	@NotNull
	@JsonProperty(required = true)
	@XmlElement(required = true, nillable = false)
	private String scheme;

	/**
	 * identifier value
	 */
	@Size(max = MAX_VALUE_LENGTH)
	@NotNull
	@JsonProperty(required = true)
	@XmlElement(required = true, nillable = false)
	private String value;

	/**
	 * period the identifier was (is) in use. Null means identifier is valid at all
	 * times
	 */
	@JsonProperty(required = true)
	@XmlElement(required = true)
	private Period period;

}
